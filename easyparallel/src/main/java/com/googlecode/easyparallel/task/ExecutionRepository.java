package com.googlecode.easyparallel.task;

public interface ExecutionRepository {

	public void save(Execution execution);

	public long registerExecutor();
}
