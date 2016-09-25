package com.smarterama.zhevaha.anagram;

public class Anagram {

	public String convertsText(String inputText) {

<<<<<<< HEAD
		try {
			String reversedWords = reversesWords(inputText);

			String result = permutesNotLetterValues(reversedWords, inputText);

			return result;

		} catch (Exception e) {
			// TODO: handle exception
			return "Error\n" + "Enter correct text";
		}

	}

	private String permutesNotLetterValues(String reversedWords,
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

	private String reversesWords(String inputText) {
=======
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
>>>>>>> 6e310583c06c8521587b309cdca33ec8de97e366

		String[] splitText = inputText.split(" ");

		StringBuilder resultOfMethod = new StringBuilder();
		for (String word : splitText) {
			resultOfMethod.append(new StringBuilder(word).reverse());
			resultOfMethod.append(" ");
		}
		resultOfMethod.deleteCharAt(resultOfMethod.length() - 1);
<<<<<<< HEAD
		return resultOfMethod.toString();
=======
		return resultOfMethod;
>>>>>>> 6e310583c06c8521587b309cdca33ec8de97e366
	}

}