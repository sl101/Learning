package com.foxminded.zhevaha;

import java.util.HashMap;

public class StringParser {

	private KeeperStrings keeper;
	private String[] inputStrings;

	StringParser(String[] inputStrings) {
		keeper = new KeeperStrings();
		this.inputStrings = inputStrings;
	}

	public String composeAnalyzedOutput() {
		for (int i = 0; i < inputStrings.length; i++) {
			keeper.computeKeeperSet(inputStrings[i]);
		}

		String result = formatResultAsString(keeper.getStoredMap());
		keeper.clearHash();
		
		return result;
		
	}

	private String formatResultAsString(
			HashMap<String, HashMap<String, Integer>> storedMap) {

		StringBuilder result = new StringBuilder();

		for (int i = 0; i < inputStrings.length; i++) {
			result.append("\n" + inputStrings[i]);
			char[] letters = inputStrings[i].toCharArray();
			for (int j = 0; j < letters.length; j++) {
				if (!inputStrings[i].substring(0, j).contains(
						String.valueOf(letters[j]))) {
					result.append("\n \""
							+ letters[j]
							+ "\" - "
							+ storedMap.get(String.valueOf(inputStrings[i]))
									.get(String.valueOf(letters[j])));
				}
			}
		}

		return result.toString();
	}
}
