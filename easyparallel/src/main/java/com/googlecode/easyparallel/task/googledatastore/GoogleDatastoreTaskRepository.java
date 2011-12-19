package com.googlecode.easyparallel.task.googledatastore;

import static com.google.appengine.api.datastore.KeyFactory.keyToString;

import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.googlecode.easyparallel.task.Task;
import com.googlecode.easyparallel.task.TaskRepository;

public class GoogleDatastoreTaskRepository implements TaskRepository {

	@Override
	public void save(Task task) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Entity taskEntity = new Entity("Task");

		taskEntity.setProperty("splitFunction", task.getSplitFunction());
		taskEntity.setProperty("computeFunction", task.getComputeFunction());
		taskEntity.setProperty("mergeFunction", task.getMergeFunction());
		Key key = datastore.put(taskEntity);

		if (task.getUniqueIdentifier() == null) {
			task.setUniqueIdentifier(keyToString(key));
		}
	}

	@Override
	public List<Task> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
