package com.googlecode.easyparallel.task;

import java.util.List;

public interface TaskRepository {

	public void save(Task task);
	
	public List<Task> getAll();
}
