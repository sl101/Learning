package com.foxminded.zhevaha.collections;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class UniqueSymbols {
	private final static int CACHE_CAPACITY_SIZE = 10;
	private Map<String, String> cache;

	public UniqueSymbols() {
		cache = new LinkedHashMap<String, String>();
	}

	public String count(String input) {
		if (cache.containsKey(input)) {
			String uniqueSimbols = cache.get(input);
			cache.remove(input);
			cache.put(input, uniqueSimbols);
		} else {
			cache.put(input, computeLettersSet(input).toString());
			checkCacheCapacity();
		}
		return cache.get(input);
	}

	private void checkCacheCapacity() {
		if (cache.size() > CACHE_CAPACITY_SIZE) {
			Iterator<String> firstValue = cache.keySet().iterator();
			cache.remove(firstValue.next());
		}
	}

	private Map<String, Integer> computeLettersSet(String input) {
		Map<String, Integer> lettersSet = new LinkedHashMap<String, Integer>();
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
}
