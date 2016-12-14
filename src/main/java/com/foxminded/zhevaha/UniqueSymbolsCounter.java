package com.foxminded.zhevaha;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniqueSymbolsCounter {

	private final static int CACHE_CAPACITY_SIZE = 10;
	private List<String> cacheCapacity;
	private CacheMap cacheMap;

	public UniqueSymbolsCounter() {
		cacheMap = new CacheMap();
		cacheCapacity = new ArrayList<String>();
	}

	public String countUniqueSymbols(String inputString) {

		checkCacheCapacity(inputString);

		return cacheMap.getCache().get(inputString).toString();
	}

	private void checkCacheCapacity(String inputString) {
		if (cacheCapacity.contains(inputString)) {
			cacheCapacity.remove(cacheCapacity.indexOf(inputString));
			cacheCapacity.add(0, inputString);

		} else {
			cacheCapacity.add(0, inputString);
			cacheMap.setCache(inputString,
					cacheMap.formatUniqueCharacters(inputString));
		}
		if (cacheCapacity.size() > CACHE_CAPACITY_SIZE) {
			cacheMap.getCache().remove(cacheCapacity.get(CACHE_CAPACITY_SIZE));
			cacheCapacity.remove(CACHE_CAPACITY_SIZE);

		}

	}

	public CacheMap getCacheMap() {
		return cacheMap;
	}

	public List<String> getCacheCapacity() {
		return cacheCapacity;
	}

}
