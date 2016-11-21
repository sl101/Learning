package com.foxminded.zhevaha;

import java.util.HashMap;

public class KeeperStrings {

	private HashMap<String, HashMap<String, Integer>> storedMap;

	public KeeperStrings() {
		storedMap = new HashMap<String, HashMap<String, Integer>>();
	}

	public void computeKeeperSet(String inputString) {
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

}
