package com.googlecode.easyparallel.task;

public class Execution {

	private final Task task;
	private String uniqueIdentifier;
	private final int requestedExecutorsCount;
	private int registeredExecutorsCount;

	public int getRegisteredExecutorsCount() {
		return registeredExecutorsCount;
	}

	public void setRegisteredExecutorsCount(int registeredExecutorsCount) {
		this.registeredExecutorsCount = registeredExecutorsCount;
	}

	public Task getTask() {
		return task;
	}

	public int getRequestedExecutorsCount() {
		return requestedExecutorsCount;
	}

	public Execution(Task task, int requestedExecutorsCount) {
		super();
		this.task = task;
		this.requestedExecutorsCount = requestedExecutorsCount;
	}

	public String getUniqueIdentifier() {
		return uniqueIdentifier;
	}

	public void setUniqueIdentifier(String uniqueIdentifier) {
		this.uniqueIdentifier = uniqueIdentifier;
	}
}
