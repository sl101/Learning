package com.foxminded.zhevaha;

import java.util.HashMap;

public class StringUniqueValues {
	private HashMap<String, Integer> values;

	public StringUniqueValues(String decompositionString) {
		values = new HashMap<String, Integer>();
	}

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
		return lettersSet;
	}

	public HashMap<String, Integer> getValues() {
		return values;
	}

	public void setValues(String inputString) {
		this.values = computeLettersSet(inputString);
	}

}
