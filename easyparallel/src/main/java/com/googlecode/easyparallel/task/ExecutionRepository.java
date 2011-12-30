package com.googlecode.easyparallel.task;

import java.util.List;

public interface ExecutionRepository {

	public void save(Execution execution);

	public List<Execution> getAll();

	public long registerExecutor();

	public abstract void remove(String uniqueId);
}
