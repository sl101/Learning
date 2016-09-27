package com.smarterama.zhevaha.anagram;

public class Anagram {

	public String reverseAlphabeticalText(String inputText) {

		if (inputText == null || (inputText.length()==1 && inputText.contains(" "))
				|| inputText.isEmpty()) {
			return "Error\n" + "Enter some text";
		} else {
			
			String reversedWords = reverseWords(inputText);

			String result = permuteNumericValues(reversedWords, inputText);

			return result;
		}

	}

	private String permuteNumericValues(String reversedWords,
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

		StringBuilder resultOfMethod = new StringBuilder();
		for (String word : splitText) {
			resultOfMethod.append(new StringBuilder(word).reverse());
			resultOfMethod.append(" ");
		}
		resultOfMethod.deleteCharAt(resultOfMethod.length() - 1);
		return resultOfMethod.toString();
	}

}