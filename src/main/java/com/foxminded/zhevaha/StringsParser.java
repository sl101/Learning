package com.foxminded.zhevaha;

import java.util.ArrayList;
import java.util.HashMap;

public class StringsParser {

	private final int STORAGE_INCUBATOR_LIMIT = 10;
	private HashMap<String, StringUniqueValues> storage;
	private ArrayList<String> storageIncubator;

	public StringsParser() {
		storage = new HashMap<String, StringUniqueValues>();
		storageIncubator = new ArrayList<String>();
	}

	public String composeOutput(String inputString) {

		createCacheMap(inputString);

		String result = formatResultAsString(inputString);

		return result;

	}

	private void createCacheMap(String inputString) {
		StringUniqueValues stringValues = new StringUniqueValues(inputString);

		if (storageIncubator.contains(inputString)) {

			storageIncubator.remove(storageIncubator.indexOf(inputString));
			storageIncubator.add(0, inputString);

		} else {

			stringValues.setValues(inputString);
			storage.put(inputString, stringValues);

			if (storageIncubator.size() < STORAGE_INCUBATOR_LIMIT) {
				storageIncubator.add(0, inputString);
			} else {
				storageIncubator.remove(STORAGE_INCUBATOR_LIMIT);
				storage.remove(inputString);
			}
		}
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

	public HashMap<String, StringUniqueValues> getStorage() {
		return storage;
	}

	public void setStorage(HashMap<String, StringUniqueValues> storage) {
		this.storage = storage;
	}

}
