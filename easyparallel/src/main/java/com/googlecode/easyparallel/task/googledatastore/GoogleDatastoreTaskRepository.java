package com.googlecode.easyparallel.task.googledatastore;

import static com.google.appengine.api.datastore.DatastoreServiceFactory.getDatastoreService;
import static com.google.appengine.api.datastore.FetchOptions.Builder.withDefaults;
import static com.google.appengine.api.datastore.KeyFactory.keyToString;
import static com.google.appengine.api.datastore.KeyFactory.stringToKey;
import static java.util.Collections.reverse;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import com.googlecode.easyparallel.task.Task;
import com.googlecode.easyparallel.task.TaskRepository;

public class GoogleDatastoreTaskRepository implements TaskRepository {

	private final DatastoreService datastore = getDatastoreService();

	@Override
	public void save(Task task) {
		Entity taskEntity = new Entity("Task");

		taskEntity.setProperty("name", task.getName());
		taskEntity.setProperty("performFunction", task.getPerformFunction());
		taskEntity.setProperty("mergeFunction", task.getMergeFunction());

		// thanks to the explicit tx, the commit can be easily controlled
		Transaction tx = datastore.beginTransaction();
		Key key = datastore.put(taskEntity);
		tx.commit();

		if (task.getUniqueIdentifier() == null) {
			task.setUniqueIdentifier(keyToString(key));
		}
	}

	@Override
	public List<Task> getAll() {
		List<Entity> taskEntities = datastore.prepare(new Query("Task"))
				.asList(withDefaults());

		List<Task> tasks = new ArrayList<Task>(taskEntities.size());
		if (!taskEntities.isEmpty()) {

			reverse(taskEntities);
			for (Entity t : taskEntities) {
				tasks.add(taskFromEntity(t));
			}
		}
		return tasks;
	}

	private Task taskFromEntity(Entity t) {
		return new Task((String) t.getProperty("name"),
				(String) t.getProperty("performFunction"),
				(String) t.getProperty("mergeFunction"),
				keyToString(t.getKey()));
	}

	@Override
	public void removeWithRelatedExecutions(String id) {
		Transaction tx = datastore.beginTransaction();
		datastore.delete(stringToKey(id));
		tx.commit();

		// TODO: remove executions here
	}

	@Override
	public Task getById(String uniqueId) {
		try {
			Entity fromDatastore = datastore.get(KeyFactory.stringToKey(uniqueId));
			return taskFromEntity(fromDatastore);
		} catch (EntityNotFoundException e) {
			return null;
		}
	}
}
