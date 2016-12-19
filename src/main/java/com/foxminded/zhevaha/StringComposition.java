package com.foxminded.zhevaha;

import java.util.HashMap;
import java.util.Map;

public class StringComposition {
	private String inputString;

	public StringComposition(String inputString) {
		this.inputString = inputString;
	}

	public String formatUniqueCharacters() {
		StringBuilder result = new StringBuilder();
		result.append(this.inputString);
		char[] letters = this.inputString.toCharArray();
		for (int i = 0; i < letters.length; i++) {
			if (!this.inputString.substring(0, i).contains(
					String.valueOf(letters[i]))) {
				result.append("\n \"" + letters[i] + "\" - "
						+ computeLettersSet().get(String.valueOf(letters[i])));
			}
		}
		return result.toString();
	}

	private Map<String, Integer> computeLettersSet() {
		Map<String, Integer> lettersSet = new HashMap<String, Integer>();
		char[] letters = this.inputString.toCharArray();

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
}
