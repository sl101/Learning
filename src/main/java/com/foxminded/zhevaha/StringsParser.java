package com.foxminded.zhevaha;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class StringsParser {

	private final static int ITERATIONS_NUMBER = 1000;
	private final static String HELLO_WORlD = "Hello World!";
	private HashMap<String, StringUniqueValues> storage;

	public StringsParser() {
		storage = new HashMap<String, StringUniqueValues>();
	}

	public String composeOutput(String[] inputStrings) {

		for (int i = 0; i < ITERATIONS_NUMBER; i++) {
			Collections.shuffle(Arrays.asList(inputStrings));
			createCacheMap(inputStrings[0]);
			cleanHash();
		}

		String result = formatResultAsString(HELLO_WORlD);
		return result;

	}

	private void createCacheMap(String inputString) {
		if (storage.containsKey(inputString)) {
			storage.get(inputString).setIterationTime(
					System.currentTimeMillis());
		} else {
			HashMap<String, Integer> newMap = computeLettersSet(inputString);
			StringUniqueValues entity = new StringUniqueValues(newMap,
					System.currentTimeMillis());
			storage.put(inputString, entity);
		}
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
		HashMap<String, StringUniqueValues> storageClone = (HashMap<String, StringUniqueValues>) storage
				.clone();

		for (String key : storage.keySet()) {
			if (storage.get(key).isExpired()
					&& !key.equals(new StringsParser().getHelloWorld())) {
				storageClone.remove(key);
			}
		}
		storage.clear();
		storage = (HashMap<String, StringUniqueValues>) storageClone.clone();

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
