package com.googlecode.easyparallel.task;

public class Execution {

	private final Task task;
	private String uniqueIdentifier;
	private final long requestedExecutorsCount;
	private long registeredExecutorsCount;

	public long getRegisteredExecutorsCount() {
		return registeredExecutorsCount;
	}

	public void setRegisteredExecutorsCount(int registeredExecutorsCount) {
		this.registeredExecutorsCount = registeredExecutorsCount;
	}

	public Task getTask() {
		return task;
	}

	public long getRequestedExecutorsCount() {
		return requestedExecutorsCount;
	}

	public Execution(Task task, int requestedExecutorsCount) {
		super();
		this.task = task;
		this.requestedExecutorsCount = requestedExecutorsCount;
	}

	public Execution(Task task, long requestedExecutorsCount,
			long registeredExecutorsCount, String uniqueId) {
		super();
		this.task = task;
		this.requestedExecutorsCount = requestedExecutorsCount;
		this.registeredExecutorsCount = registeredExecutorsCount;
		this.uniqueIdentifier = uniqueId;
	}

	public String getUniqueIdentifier() {
		return uniqueIdentifier;
	}

	public void setUniqueIdentifier(String uniqueIdentifier) {
		this.uniqueIdentifier = uniqueIdentifier;
	}
}
