package com.googlecode.easyparallel.task;

import java.util.List;

public interface TaskRepository {

	public void save(Task task);
	
	public List<Task> getAll();

	public abstract void removeWithRelatedExecutions(String id);
}
