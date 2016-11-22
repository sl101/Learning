package com.foxminded.zhevaha;

import java.util.ArrayList;
import java.util.HashMap;

public class KeeperStrings {

	private HashMap<String, HashMap<String, Integer>> storedMap;
	private HashMap<String, Integer> inputStringsLoaded;
	private ArrayList<String> loadedStrings = new ArrayList<String>();
	private final int MAX_VALUE = 100;
	private final int FILTER_VALUE = 20;
	

	public KeeperStrings() {
		storedMap = new HashMap<String, HashMap<String, Integer>>();
		inputStringsLoaded = new HashMap<String, Integer>();
		loadedStrings = new ArrayList<String>();
	}

	public void computeKeeperSet(String inputString) {
		loadedStrings.add(inputString);
		if (!inputStringsLoaded.containsKey(inputString)) {
			inputStringsLoaded.put(inputString, 1);

		} else {
			inputStringsLoaded.put(inputString,
					inputStringsLoaded.get(inputString) + 1);
		}

		if (!storedMap.containsKey(inputString)) {
			storedMap.put(inputString, computeLettersSet(inputString));
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

	public HashMap<String, HashMap<String, Integer>> getStoredMap() {
		return storedMap;
	}

	public void cleanHash() {
		if (loadedStrings.size() > MAX_VALUE) {
			for (int i = 0; i < loadedStrings.size(); i++) {
				if (inputStringsLoaded.get(loadedStrings.get(i)) < FILTER_VALUE) {
					storedMap.remove(loadedStrings.get(i));
				}
			}

			loadedStrings.clear();
			inputStringsLoaded.clear();
		}
	}
}
