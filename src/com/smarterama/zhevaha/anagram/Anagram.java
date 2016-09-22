package com.smarterama.zhevaha.anagram;

public class Anagram {

	public String convertsText(String inputText) {

		StringBuilder reversedText = reversesWords(inputText);

		StringBuilder resultOfMethod = permutesNotLetterValues(
				reversedText.toString(), inputText);

		return resultOfMethod.toString();

	}

	private StringBuilder permutesNotLetterValues(
			String reversedText, String inputText) {

		char[] arrayReversedText = reversedText
				.toCharArray();
		StringBuilder resultOfMethod = new StringBuilder();
		for (char arrayElement : arrayReversedText) {
			if (new Character(arrayElement)
					.isLetter(arrayElement)) {
				resultOfMethod.append(arrayElement);
			}
		}

		char[] arrayInputText = inputText.toCharArray();
		for (int i = 0; i < inputText.length(); i++) {
			if (!new Character(arrayReversedText[i])
					.isLetter(arrayInputText[i])) {
				resultOfMethod.insert(i, arrayInputText[i]);
			}

		}
		return resultOfMethod;
	}

	private StringBuilder reversesWords(String inputText) {

		String[] splitText = inputText.split(" ");

		StringBuilder resultOfMethod = new StringBuilder();
		for (String word : splitText) {
			resultOfMethod.append(new StringBuilder(word).reverse());
			resultOfMethod.append(" ");
		}
		resultOfMethod.deleteCharAt(resultOfMethod.length() - 1);
		return resultOfMethod;
	}

}
