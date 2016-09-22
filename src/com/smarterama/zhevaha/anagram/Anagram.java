package com.smarterama.zhevaha.anagram;

public class Anagram {

	public String reversesLettersOfWordInPhrase(String startPhrase) {

		StringBuilder reverseWordsInPhrase = reversesWords(startPhrase);

		StringBuilder resultPhrase = returnsSymbolsOnOriginalPosition(
				reverseWordsInPhrase.toString(), startPhrase);

		return resultPhrase.toString();

	}

	private StringBuilder returnsSymbolsOnOriginalPosition(
			String reversedWordsInStartPhrase, String startPhrase) {

		char[] charVolumeOfReversedWordsInStartPhrase = reversedWordsInStartPhrase
				.toCharArray();
		StringBuilder methodResult = new StringBuilder();
		for (char volumeOfReversePhrase : charVolumeOfReversedWordsInStartPhrase) {
			if (new Character(volumeOfReversePhrase)
					.isLetter(volumeOfReversePhrase)) {
				methodResult.append(volumeOfReversePhrase);
			}
		}

		char[] charVolumeOfStartCharArray = startPhrase.toCharArray();
		for (int i = 0; i < startPhrase.length(); i++) {
			if (!new Character(charVolumeOfReversedWordsInStartPhrase[i])
					.isLetter(charVolumeOfStartCharArray[i])) {
				methodResult.insert(i, charVolumeOfStartCharArray[i]);
			}

		}
		return methodResult;
	}

	private StringBuilder reversesWords(String inputPhrase) {

		String[] splitInputPhrase = inputPhrase.split(" ");

		StringBuilder reverseWords = new StringBuilder();
		for (String word : splitInputPhrase) {
			reverseWords.append(new StringBuilder(word).reverse());
			reverseWords.append(" ");
		}
		reverseWords.deleteCharAt(reverseWords.length() - 1);
		return reverseWords;
	}

}
