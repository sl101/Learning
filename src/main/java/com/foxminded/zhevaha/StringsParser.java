package com.foxminded.zhevaha;

import java.util.ArrayList;
import java.util.HashMap;

public class StringsParser {

	private final static int CACHE_CAPACITY = 10;
	private final static String HELLO_WORlD = "Hello World!";
	private HashMap<String, StringUniqueValues> cache;
	private ArrayList<String> cacheIncubator;

	public StringsParser() {
		cache = new HashMap<String, StringUniqueValues>();
		cacheIncubator = new ArrayList<String>();
	}

	public String composeOutput(String exampleValue) {

		createCacheMap(exampleValue);
		String result = formatResultAsString(HELLO_WORlD);

		return result;

	}

	private void createCacheMap(String inputString) {
		StringUniqueValues stringValues = new StringUniqueValues();

		if (cacheIncubator.contains(inputString)) {

			cacheIncubator.remove(cacheIncubator.indexOf(inputString));
			cacheIncubator.add(0, inputString);

		} else {

			stringValues.setValues(inputString);
			cache.put(inputString, stringValues);

			if (cacheIncubator.size() < CACHE_CAPACITY) {
				cacheIncubator.add(0, inputString);
			} else {
				cacheIncubator.remove(CACHE_CAPACITY);
				cache.remove(inputString);
			}
		}
	}

	private String formatResultAsString(String defaultWord) {

		StringBuilder result = new StringBuilder();
		if (cache.containsKey(defaultWord)) {
			result.append(defaultWord);
			char[] letters = defaultWord.toCharArray();
			for (int i = 0; i < letters.length; i++) {
				if (!defaultWord.substring(0, i).contains(
						String.valueOf(letters[i]))) {
					result.append("\n \""
							+ letters[i]
							+ "\" - "
							+ cache.get(defaultWord).getValues()
									.get(String.valueOf(letters[i])));
				}
			}

		} else {
			result.append("\n" + defaultWord + " is empty");
		}
		return result.toString();

	}

	public HashMap<String, StringUniqueValues> getCache() {
		return cache;
	}

	public void setCache(HashMap<String, StringUniqueValues> cache) {
		this.cache = cache;
	}

	public ArrayList<String> getCacheIncubator() {
		return cacheIncubator;
	}

	public void setCacheIncubator(ArrayList<String> cacheIncubator) {
		this.cacheIncubator = cacheIncubator;
	}

	
}
