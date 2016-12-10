package com.foxminded.zhevaha;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniqueSymbolsCounter {

	private final static int CACHE_CAPACITY_SIZE = 10;
	private Map<String, String> cache;
	private List<String> cacheCapacity;

	public UniqueSymbolsCounter() {
		cache = new HashMap<String, String>();
		cacheCapacity = new ArrayList<String>();
	}

	public String countUniqueSymbols(String inputString) {

		if (cache.containsKey(inputString)) {

			cacheCapacity.remove(cacheCapacity.indexOf(inputString));
			cacheCapacity.add(0, inputString);

		} else {

			if (cacheCapacity.size() < CACHE_CAPACITY_SIZE) {
				cacheCapacity.add(0, inputString);
				cache.put(inputString, formatResultAsString(inputString));
			} else {
				cacheCapacity.remove(CACHE_CAPACITY_SIZE);
				cache.remove(cacheCapacity.get(CACHE_CAPACITY_SIZE));
			}
		}

		return cache.get(inputString);

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

	private String formatResultAsString(String inputString) {
		HashMap<String, Integer> values = computeLettersSet(inputString);
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

	public HashMap<String, String> getCache() {
		return (HashMap<String, String>) cache;
	}

}
