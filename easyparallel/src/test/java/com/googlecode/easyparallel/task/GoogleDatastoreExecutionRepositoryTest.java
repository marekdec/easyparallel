package com.googlecode.easyparallel.task;

import static com.google.appengine.api.datastore.DatastoreServiceFactory.getDatastoreService;
import static com.google.appengine.api.datastore.FetchOptions.Builder.withDefaults;
import static com.google.appengine.api.datastore.KeyFactory.createKeyString;
import static org.fest.assertions.Assertions.assertThat;

import java.util.List;

import org.testng.annotations.Test;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.googlecode.easyparallel.GoogleDatastoreTest;
import com.googlecode.easyparallel.task.googledatastore.GoogleDatastoreExecutionRepository;

public class GoogleDatastoreExecutionRepositoryTest extends GoogleDatastoreTest {

	private final GoogleDatastoreExecutionRepository executionRepository = new GoogleDatastoreExecutionRepository();

	@Test
	public void testSave() {
		// given
		Task task = new Task("splitFunction", "computeFunction",
				"mergeFunction");
		task.setUniqueIdentifier(createKeyString("fake", 1));

		Execution execution = new Execution(task, 10);

		// when
		executionRepository.save(execution);

		// then
		DatastoreService ds = getDatastoreService();
		List<Entity> executionsFromDb = ds.prepare(new Query("Execution"))
				.asList(withDefaults());

		assertThat(executionsFromDb).hasSize(1);
		assertThat(executionsFromDb.get(0).getProperty("taskKey")).isNotNull();
		assertThat(
				(Long) executionsFromDb.get(0).getProperty(
						"requestedExecutorsCount")).isEqualTo(10);
		assertThat(
				(Long) executionsFromDb.get(0).getProperty(
						"registeredExecutorsCount")).isEqualTo(0);

	}

	@Test
	public void testGetByid() {
		// given

		// when
	}
}
