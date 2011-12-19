package com.googlecode.easyparallel.task;

public class Task {

	private final String splitFunction;
	private final String computeFunction;
	private final String mergeFunction;
	private String uniqueIdentifier;

	public String getSplitFunction() {
		return splitFunction;
	}

	public String getComputeFunction() {
		return computeFunction;
	}

	public String getMergeFunction() {
		return mergeFunction;
	}

	public Task(String splitFunction, String computeFunction,
			String mergeFunction) {
		super();
		this.splitFunction = splitFunction;
		this.computeFunction = computeFunction;
		this.mergeFunction = mergeFunction;
	}

	public String getUniqueIdentifier() {
		return uniqueIdentifier;
	}

	public void setUniqueIdentifier(String uniqueIdentifier) {
		this.uniqueIdentifier = uniqueIdentifier;
	}

}
