package com.googlecode.easyparallel.task;

public class Task {

	private final String performFunction;
	private final String mergeFunction;
	private final String name;
	private String uniqueIdentifier;

	public String getPerformFunction() {
		return performFunction;
	}

	public String getName() {
		return name;
	}

	public String getMergeFunction() {
		return mergeFunction;
	}

	public Task(String name, String computeFunction, String mergeFunction) {
		super();
		this.name = name;
		this.performFunction = computeFunction;
		this.mergeFunction = mergeFunction;
	}

	public Task(String name, String computeFunction, String mergeFunction,
			String uniqueIdentifier) {
		this(name, computeFunction, mergeFunction);
		this.uniqueIdentifier = uniqueIdentifier;
	}

	public String getUniqueIdentifier() {
		return uniqueIdentifier;
	}

	public void setUniqueIdentifier(String uniqueIdentifier) {
		this.uniqueIdentifier = uniqueIdentifier;
	}

}
