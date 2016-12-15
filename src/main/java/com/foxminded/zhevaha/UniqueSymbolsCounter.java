package com.foxminded.zhevaha;

import java.util.HashMap;
import java.util.Map;

public class UniqueSymbolsCounter {

	private final static int CACHE_CAPACITY_SIZE = 10;
	private Map<String, CacheMap> cache;
//	private CacheMap cacheMap;

	public UniqueSymbolsCounter() {

		cache = new HashMap<String, CacheMap>();
	}

	public String countUniqueSymbols(String inputString) {

		checkCache(inputString);

		return cache.get(inputString).getResultString();
	}

	private void checkCache(String inputString) {

		if (cache.containsKey(inputString)) {

			cache.get(inputString).setUseFrequency(CACHE_CAPACITY_SIZE);
		} else {

			CacheMap cacheMap = new CacheMap(inputString);
			cacheMap.setResultString();
			cacheMap.setUseFrequency(CACHE_CAPACITY_SIZE);
			cache.put(inputString, cacheMap);
		}
		checkCacheCapacity(inputString);
	}

	private void checkCacheCapacity(String inputString) {
		Map<String, CacheMap> cacheForRemove = new HashMap<String, CacheMap>();
		cacheForRemove.putAll(cache);

		for (String value : cache.keySet()) {

			if (!value.equals(inputString)) {
				cache.get(value).setUseFrequency(
						cache.get(value).getUseFrequency() - 1);
			}
			if (cache.get(value).getUseFrequency() <= 0) {

				cacheForRemove.remove(value);
			}
		}
		cache.clear();
		cache.putAll(cacheForRemove);
		cacheForRemove.clear();

	}

	public Map<String, CacheMap> getCache() {

		return cache;
	}

}
