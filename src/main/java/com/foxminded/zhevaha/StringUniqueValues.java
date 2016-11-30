package com.foxminded.zhevaha;

import java.util.HashMap;

public class StringUniqueValues {
	private HashMap<String, Integer> values;
	private long iterationTime;
	private final int LIFE_LIMIT = 1;

	public StringUniqueValues(HashMap<String, Integer> value, long birthTime) {
		this.values = value;
		this.iterationTime = birthTime;
	}

	public boolean isExpired() {
		return System.currentTimeMillis() > (iterationTime + LIFE_LIMIT);
	}

	public HashMap<String, Integer> getValues() {
		return values;
	}

	public void setValues(HashMap<String, Integer> values) {
		this.values = values;
	}

	public long getIterationTime() {
		return iterationTime;
	}

	public void setIterationTime(long iterationTime) {
		this.iterationTime = iterationTime;
	}

}
