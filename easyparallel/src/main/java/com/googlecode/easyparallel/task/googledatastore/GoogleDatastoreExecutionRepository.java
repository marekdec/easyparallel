package com.googlecode.easyparallel.task.googledatastore;

import static com.google.appengine.api.datastore.FetchOptions.Builder.withDefaults;
import static com.google.appengine.api.datastore.KeyFactory.keyToString;
import static com.google.appengine.api.datastore.KeyFactory.stringToKey;
import static com.google.common.collect.Lists.newArrayListWithCapacity;

import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import com.google.inject.Inject;
import com.googlecode.easyparallel.task.Execution;
import com.googlecode.easyparallel.task.ExecutionRepository;
import com.googlecode.easyparallel.task.Task;
import com.googlecode.easyparallel.task.TaskRepository;

public class GoogleDatastoreExecutionRepository implements ExecutionRepository {

	DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

	@Inject
	TaskRepository taskRepository;

	@Override
	public void save(Execution execution) {

		Entity executionEntity = new Entity(Execution.class.getSimpleName());

		executionEntity.setProperty("taskKey", KeyFactory.stringToKey(execution
				.getTask().getUniqueIdentifier()));
		executionEntity.setProperty("requestedExecutorsCount",
				execution.getRequestedExecutorsCount());
		executionEntity.setProperty("registeredExecutorsCount",
				execution.getRegisteredExecutorsCount());

		Key key = datastore.put(executionEntity);

		if (execution.getUniqueIdentifier() == null) {
			execution.setUniqueIdentifier(keyToString(key));
		}

	}

	@Override
	public long registerExecutor() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Execution> getAll() {
		List<Entity> executionEntities = datastore.prepare(
				new Query(Execution.class.getSimpleName())).asList(
				withDefaults());

		List<Execution> executions = newArrayListWithCapacity(executionEntities
				.size());

		if (!executionEntities.isEmpty()) {
			for (Entity e : executionEntities) {
				Key taskKey = (Key) e.getProperty("taskKey");

				Task task = taskRepository.getById(KeyFactory
						.keyToString(taskKey));
				Execution execution = new Execution(task,
						(Long) e.getProperty("requestedExecutorsCount"),
						(Long) e.getProperty("registeredExecutorsCount"),
						KeyFactory.keyToString(e.getKey()));

				executions.add(execution);
			}
		}
		return executions;
	}

	@Override
	public void remove(String uniqueId) {
		Transaction tx = datastore.beginTransaction();
		datastore.delete(stringToKey(uniqueId));
		tx.commit();
	}

}
