package com.smarterama.zhevaha.anagram;

public class Anagram {

	public String convertsText(String inputText) {

		if(inputText != null && !inputText.startsWith(" ") &&
						!inputText.isEmpty()){
				 String reversedWords = reversesWords(inputText);

					String result = permutesNotLetterValues(reversedWords, inputText);

					return result;
			 }
			 else{
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