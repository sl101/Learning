package com.foxminded.zhevaha;

import java.util.HashMap;
import java.util.Map;

public class CacheMap {

	private Map<String, String> cache;

	public CacheMap() {
		cache = new HashMap<String, String>();
	}

	public String formatUniqueCharacters(String inputString) {
		Map<String, Integer> values = computeLettersSet(inputString);
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

	private Map<String, Integer> computeLettersSet(String inputString) {
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

	public Map<String, String> getCache() {
		return cache;
	}

	public void setCache(String key, String value) {
		cache.put(key, value);
	}

}
