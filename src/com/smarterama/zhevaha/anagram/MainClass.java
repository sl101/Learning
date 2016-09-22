package com.smarterama.zhevaha.anagram;

public class MainClass {

	public static void main(String[] args) {

		String startPhrase = "1)It is the 1exersize.";
		System.out.println(startPhrase);

		Anagram reversedPhrase = new Anagram();

		System.out.println(reversedPhrase
				.reversesLettersOfWordInPhrase(startPhrase));
	}

}
