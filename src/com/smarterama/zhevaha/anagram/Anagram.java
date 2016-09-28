package com.smarterama.zhevaha.anagram;

public class Anagram {

	public String reverseAlphabeticalText(String inputText) {

		if (!checkNotEmptyString(inputText)) {
			return "Error\n" + "Enter some text";
		} else {

			String reversedWords = reverseWords(inputText);

			String result = permuteNotAlphabeticalSymbols(reversedWords,
					inputText);

			return result;
		}

	}

	private boolean checkNotEmptyString(String text) {
		if (text == null 
				|| text.trim().length() == 0
				|| text.isEmpty()) {
			return false;
		} 

		return true;
	}

	private String permuteNotAlphabeticalSymbols(String reversedWords,
			String inputText) {

		char[] reversedSymbols = reversedWords.toCharArray();
		StringBuilder result = new StringBuilder();
		for (char symbol : reversedSymbols) {
			if (Character.isLetter(symbol)) {
				result.append(symbol);
			}
		}

		char[] inputSymbols = inputText.toCharArray();
		for (int i = 0; i < inputText.length(); i++) {
			if (!Character.isLetter(inputSymbols[i])) {
				result.insert(i, inputSymbols[i]);
			}

		}
		return result.toString();
	}

	private String reverseWords(String inputText) {

		String[] splitText = inputText.split(" ");

		StringBuilder result = new StringBuilder();
		for (String word : splitText) {
			result.append(new StringBuilder(word).reverse());
			result.append(" ");
		}
		result.deleteCharAt(result.length() - 1);
		return result.toString();
	}

}