package com.foxminded.zhevaha;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class UniqueSymbols {
	private final static int CACHE_CAPACITY_SIZE = 10;
	private String input;
	private Map<String, String> cache;

	public UniqueSymbols() {
		cache = new LinkedHashMap<String, String>();
	}

	public String countUniqueSymbols(String input) {
		this.input = input;
		return checkCache().get(input);
	}

	private Map<String, String> checkCache() {
		if (cache.containsKey(input)) {
			String uniqueSimbols = cache.get(input);
			cache.remove(input);
			cache.put(input, uniqueSimbols);
		} else {
			cache.put(input, formatUniqueCharacters());
		}
		checkCacheCapacity();

		return cache;
	}

	private void checkCacheCapacity() {
		if (cache.size() > CACHE_CAPACITY_SIZE) {
			Iterator<String> firstValue = cache.keySet().iterator();
			cache.remove(firstValue.next());
		}
	}

	private Map<String, Integer> computeLettersSet() {
		Map<String, Integer> lettersSet = new HashMap<String, Integer>();
		char[] letters = input.toCharArray();

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

	private String formatUniqueCharacters() {
		StringBuilder result = new StringBuilder();
		result.append(input);
		char[] letters = input.toCharArray();
		for (int i = 0; i < letters.length; i++) {
			if (!input.substring(0, i).contains(String.valueOf(letters[i]))) {
				result.append("\n \"" + letters[i] + "\" - "
						+ computeLettersSet().get(String.valueOf(letters[i])));
			}
		}
		return result.toString();
	}
}
