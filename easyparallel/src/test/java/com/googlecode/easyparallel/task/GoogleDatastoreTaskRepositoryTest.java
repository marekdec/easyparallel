package com.googlecode.easyparallel.task;

import static com.google.appengine.api.datastore.KeyFactory.keyToString;
import static com.googlecode.easyparallel.task.googledatastore.fixture.EntityCreator.forType;
import static org.fest.assertions.Assertions.assertThat;

import org.testng.annotations.Test;

import com.googlecode.easyparallel.GoogleDatastoreTest;
import com.googlecode.easyparallel.task.googledatastore.GoogleDatastoreTaskRepository;

public class GoogleDatastoreTaskRepositoryTest extends GoogleDatastoreTest {

	private final GoogleDatastoreTaskRepository taskRepository = new GoogleDatastoreTaskRepository();

	@Test
	public void testSave() {

	}

	@Test
	public void testGetByid() {
		// given
		String keyToLookFor = keyToString(forType(Task.class)
				.with("name", "sample name")
				.with("mergeFunction", "merge function")
				.with("performFunction", "perform function")
				.createAndPersistEntity().getKey());

		// when
		Task foundTask = taskRepository.getById(keyToLookFor);

		// then
		assertThat(foundTask).isNotNull();
	}
}
