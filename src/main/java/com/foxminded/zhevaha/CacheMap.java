package com.foxminded.zhevaha;

import java.util.HashMap;
import java.util.Map;

public class CacheMap {

	private String inputString;
	private String resultString;
	private int useFrequency;

	public CacheMap(String inputString) {
		this.inputString = inputString;
	}

	public String formatUniqueCharacters() {
		Map<String, Integer> values = computeLettersSet();
		StringBuilder result = new StringBuilder();
		result.append(inputString);
		char[] letters = inputString.toCharArray();
		for (int i = 0; i < letters.length; i++) {
			if (!inputString.substring(0, i).contains(
					String.valueOf(letters[i]))) {
				result.append("\n \"" + letters[i] + "\" - "
						+ values.get(String.valueOf(letters[i])));
			}
		}
		return result.toString();

	}

	private Map<String, Integer> computeLettersSet() {
		Map<String, Integer> lettersSet = new HashMap<String, Integer>();
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

	public String getResultString() {
		return resultString;
	}

	public void setResultString() {
		this.resultString = formatUniqueCharacters();
	}

	public int getUseFrequency() {
		return useFrequency;
	}

	public void setUseFrequency(int useFrequency) {
		this.useFrequency = useFrequency;
	}

}
