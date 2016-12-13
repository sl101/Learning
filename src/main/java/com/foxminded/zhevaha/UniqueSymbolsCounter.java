package com.foxminded.zhevaha;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniqueSymbolsCounter {

	private final static int CACHE_CAPACITY_SIZE = 10;
//	private Map <String, CacheMap> cacheMap;
	private List<String> cacheCapacity;
	private CacheMap cacheMap;

	public UniqueSymbolsCounter() {
		cacheMap = new CacheMap(); 
//		cacheMap = new HashMap<String, CacheMap>();
		cacheCapacity = new ArrayList<String>();
	}

	public String countUniqueSymbols(String inputString) {

//		if (!cacheMap.entrySet().contains(inputString)) {
		if (cacheCapacity.contains(inputString)) {
//			cacheCapacity.remove(cacheCapacity.indexOf(inputString));
//			cacheCapacity.add(0, inputString);
//			CacheMap cache = new CacheMap();
//			cache.setCacheCapacity(1);
//			cache.setCache(inputString, formatResultAsString(inputString));
//			cacheMap.put(inputString, cache);
////			setCacheCapacity(cache.getCacheCapacity()+1);
//			int minimumCache = 1;
//			while (cacheMap.size()>CACHE_CAPACITY_SIZE){
//				
//				for (String value:cacheMap.keySet()) {
//					if (cacheMap.get(value).getCacheCapacity()<=minimumCache) {
//						minimumCache = cacheMap.get(value).getCacheCapacity();
//					}
//					
//					
//				}
//			}
//		} else {

			if (cacheCapacity.size() < CACHE_CAPACITY_SIZE) {
				cacheCapacity.remove(cacheCapacity.indexOf(inputString));
				cacheCapacity.add(0, inputString);
//				cache.setCache(inputString, formatAsUniqueCharacters(inputString));
			} else {
				cacheCapacity.remove(CACHE_CAPACITY_SIZE);
				cacheMap.getCache().remove(cacheCapacity.get(CACHE_CAPACITY_SIZE));
			}
			
//			if (cacheMap.get(inputString).getCacheCapacity() < 	CACHE_CAPACITY_SIZE) {
//				cache.setCacheCapacity(cache.getCacheCapacity()+1);
//				cache.put(inputString, formatResultAsString(inputString));
			} else {
				cacheCapacity.add(inputString);
				cacheMap.setCache(inputString, formatAsUniqueCharacters(inputString));
//				cacheCapacity.remove(CACHE_CAPACITY_SIZE);
//				cache.remove(cacheCapacity.get(CACHE_CAPACITY_SIZE));
//				cacheMap.remove(inputString);
			}
//		}
//
		return cacheMap.getCache().get(inputString).toString();
//
	}

//	private HashMap<String, Integer> computeLettersSet(String inputString) {
//		HashMap<String, Integer> lettersSet = new HashMap<String, Integer>();
//		char[] letters = inputString.toCharArray();
//
//		for (char letter : letters) {
//			if (lettersSet.containsKey(String.valueOf(letter))) {
//				lettersSet.put(String.valueOf(letter),
//						lettersSet.get(String.valueOf(letter)) + 1);
//			} else {
//				lettersSet.put(String.valueOf(letter), 1);
//			}
//		}
//		return lettersSet;
//	}

	private String formatAsUniqueCharacters(String inputString) {
		HashMap<String, Integer> values = cacheMap.computeLettersSet(inputString);
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

	public CacheMap getCacheMap() {
		return cacheMap;
	}

//	public HashMap<String, String> getCache() {
//		return (HashMap<String, String>) cache;
//	}

	
}
