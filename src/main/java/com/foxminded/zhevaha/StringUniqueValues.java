package com.foxminded.zhevaha;

import java.util.HashMap;

public class StringUniqueValues {
	private HashMap<String, Integer> values;
	private long iterationTime;

	// private final int LIFE_LIMIT = 1;

	public StringUniqueValues(String decompositionString) {
		values = new HashMap<String, Integer>();
		// this.iterationTime = System.currentTimeMillis();
	}

	// public boolean isExpired() {
	// return System.currentTimeMillis() > (iterationTime + LIFE_LIMIT);
	// }

	private HashMap<String, Integer> computeLettersSet(String inputString) {
		HashMap<String, Integer> lettersSet = new HashMap<String, Integer>();
		char[] letters = inputString.toCharArray();

		for (char letter : letters) {
			if (lettersSet.containsKey(String.valueOf(letter))) {
				lettersSet.put(String.valueOf(letter),
						lettersSet.get(String.valueOf(letter)) + 1);
			} else {
				lettersSet.put(String.valueOf(letter), 1);
			}
		}
		this.iterationTime = System.currentTimeMillis();
		return lettersSet;
	}

	public HashMap<String, Integer> getValues() {
		return values;
	}

	public void setValues(String inputString) {
		this.values = computeLettersSet(inputString);
	}

	public long getIterationTime() {
		return iterationTime;
	}

	public void setIterationTime(long iterationTime) {
		this.iterationTime = iterationTime;
	}

}
