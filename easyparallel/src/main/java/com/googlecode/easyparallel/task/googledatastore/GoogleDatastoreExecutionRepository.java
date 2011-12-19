package com.googlecode.easyparallel.task.googledatastore;

import static com.google.appengine.api.datastore.KeyFactory.keyToString;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.googlecode.easyparallel.task.Execution;
import com.googlecode.easyparallel.task.ExecutionRepository;

public class GoogleDatastoreExecutionRepository implements ExecutionRepository {

	@Override
	public void save(Execution execution) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Entity executionEntity = new Entity("Execution");

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

}
