package com.foxminded.zhevaha;

import java.util.HashMap;
import java.util.Random;

public class StringsParser {

	private Cache cache;
	private final long AMOUNT_ITERATIONS = 100000;

	public StringsParser() {
		cache = new Cache();
	}

	public String composeOutput(String[] inputStrings) {

		for (int i = 0; i < AMOUNT_ITERATIONS; i++) {
			StringBuilder buildInputString = new StringBuilder();
			for (int j = 0; j < inputStrings.length; j++) {
				buildInputString.append(inputStrings[new Random()
						.nextInt(inputStrings.length)]);
			}
			createCacheMap(buildInputString.toString());
			cleanHash();
		}

		String result = formatResultAsString(Cache.getStorage());
		return result;

	}

	private void createCacheMap(String inputString) {

		if (Cache.getStorage().containsKey(inputString)) {
			Cache.getStorage().get(inputString)
					.setBirthTime(System.currentTimeMillis());
		} else {
			HashMap<String, Integer> newMap = computeLettersSet(inputString);
			Entity entity = new Entity(newMap, System.currentTimeMillis());
			Cache.getStorage().put(inputString, entity);
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

	private String formatResultAsString(HashMap<String, Entity> valuesMap) {

		StringBuilder result = new StringBuilder();
		for (String key : valuesMap.keySet()) {
			result.append("\n" + key);
			char[] letters = key.toCharArray();
			for (int i = 0; i < letters.length; i++) {
				if (!key.substring(0, i).contains(String.valueOf(letters[i]))) {
					result.append("\n \""
							+ letters[i]
							+ "\" - "
							+ valuesMap.get(key).getValue()
									.get(String.valueOf(letters[i])));
				}
			}
		}

		return result.toString();
	}

	private void cleanHash() {
		Cache.expire();
	}
}
