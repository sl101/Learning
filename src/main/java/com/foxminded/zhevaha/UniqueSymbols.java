package com.foxminded.zhevaha;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class UniqueSymbols {
	private final static int CACHE_CAPACITY_SIZE = 10;
	private Map<String, String> cache;

	public UniqueSymbols() {
		cache = new LinkedHashMap<String, String>();
	}

	public String countUniqueSymbols(String inputString) {
//		cache.put(inputString, checkCache(inputString).get(inputString));
//		return cache.get(inputString);
		return checkCache(inputString).get(inputString);
	}

	private Map<String, String> checkCache(String inputString) {
		if (cache.containsKey(inputString)) {
			
			String uniqueSimbols = cache.get(inputString);
			cache.remove(inputString);
			cache.put(inputString, uniqueSimbols);
		} else {
			cache.put(inputString,
					new StringComposition(inputString).formatUniqueCharacters());
		}
		return checkCacheCapacity(inputString);
	}

	private Map<String, String> checkCacheCapacity(String inputString) {
		if (cache.size() > CACHE_CAPACITY_SIZE) {
			Iterator<String> firstValue = cache.keySet().iterator();
			cache.remove(firstValue.next());
		}
		return cache;
	}
}
