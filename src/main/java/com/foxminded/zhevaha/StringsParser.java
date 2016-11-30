package com.foxminded.zhevaha;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class StringsParser {

	private final static int ITERATIONS_NUMBER = 10000;
	private final int LIFE_LIMIT = 1;
	private final static String HELLO_WORlD = "Hello World!";
	private HashMap<String, StringUniqueValues> storage;

	public StringsParser() {
		storage = new HashMap<String, StringUniqueValues>();
	}

	public String composeOutput(String[] inputStrings) {

		for (int i = 0; i < ITERATIONS_NUMBER; i++) {
			Collections.shuffle(Arrays.asList(inputStrings));
			createCacheMap(inputStrings[0]);
		}

		String result = formatResultAsString(HELLO_WORlD);
		return result;

	}

	private void createCacheMap(String inputString) {
		StringUniqueValues stringValues = new StringUniqueValues(inputString);
		if (storage.containsKey(inputString)) {
			storage.get(inputString).setIterationTime(
					System.currentTimeMillis());
		} else {
			stringValues.setValues(inputString);
			storage.put(inputString, stringValues);
		}
		cleanHash();
	}

	private String formatResultAsString(String defoltWord) {

		StringBuilder result = new StringBuilder();
		if (storage.containsKey(defoltWord)) {
			result.append(defoltWord);
			char[] letters = defoltWord.toCharArray();
			for (int i = 0; i < letters.length; i++) {
				if (!defoltWord.substring(0, i).contains(
						String.valueOf(letters[i]))) {
					result.append("\n \""
							+ letters[i]
							+ "\" - "
							+ storage.get(defoltWord).getValues()
									.get(String.valueOf(letters[i])));
				}
			}

		} else {
			result.append("\n" + defoltWord + " is empty");
		}
		return result.toString();

	}

	private void cleanHash() {
		HashMap<String, StringUniqueValues> storageClone = new HashMap<String, StringUniqueValues>(
				storage);

		for (String key : storage.keySet()) {
			if (isExpired(key) && !key.equals(HELLO_WORlD)) {
				storageClone.remove(key);
			}
		}
		storage.clear();
		storage = new HashMap<String, StringUniqueValues>(storageClone);

	}

	private boolean isExpired(String key) {
		return System.currentTimeMillis() > (storage.get(key)
				.getIterationTime() + LIFE_LIMIT);
	}

	public static String getHelloWorld() {
		return HELLO_WORlD;
	}

	public HashMap<String, StringUniqueValues> getStorage() {
		return storage;
	}

	public void setStorage(HashMap<String, StringUniqueValues> storage) {
		this.storage = storage;
	}

}
