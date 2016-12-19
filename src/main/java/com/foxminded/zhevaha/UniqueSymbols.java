package com.foxminded.zhevaha;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class UniqueSymbols {
	private final static int CACHE_CAPACITY_SIZE = 10;
	private Map<String, String> cache;

	public UniqueSymbols() {
		cache = new LinkedHashMap<String, String>();
	}

	public String countUniqueSymbols(String inputString) {
		checkCache(inputString);
		return cache.get(inputString);
	}

	private void checkCache(String inputString) {
		if (cache.containsKey(inputString)) {
			String uniqueSimbols = cache.get(inputString);
			cache.remove(inputString);
			cache.put(inputString, uniqueSimbols);
		} else {
			cache.put(inputString,
					new StringComposition(inputString).formatUniqueCharacters());
		}
		checkCacheCapacity(inputString);
	}

	private void checkCacheCapacity(String inputString) {
		if (cache.size() > CACHE_CAPACITY_SIZE) {
			Iterator<String> firstValue = cache.keySet().iterator(); 
			cache.remove(firstValue.next());
		}
	}

	public Map<String, String> getCache() {
		return cache;
	}
	
	
}
