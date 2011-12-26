package com.googlecode.easyparallel.task.googledatastore.fixture;

import static com.google.appengine.api.datastore.DatastoreServiceFactory.getDatastoreService;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Transaction;

public class EntityCreator {

	private final String entityName;

	private final Map<String, Object> properties = new HashMap<String, Object>();

	public EntityCreator(String entityName) {
		super();
		this.entityName = entityName;
	}

	public static EntityCreator forType(Class<?> typeOfEntity) {
		return new EntityCreator(typeOfEntity.getSimpleName());
	}

	public EntityCreator with(String name, Object value) {
		properties.put(name, value);
		return this;
	}

	public Entity createEntity() {
		Entity entity = new Entity(entityName);

		for (Entry<String, Object> property : properties.entrySet()) {
			entity.setProperty(property.getKey(), property.getValue());
		}

		return entity;
	}

	public Entity createAndPersistEntity() {
		DatastoreService ds = getDatastoreService();
		Transaction tx = ds.beginTransaction();
		Entity entity = createEntity();
		ds.put(entity);
		tx.commit();
		return entity;
	}

}
