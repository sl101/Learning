package com.smarterama.zhevaha.anagram;

public class MainClass {

	public static void main(String[] args) {

		String somePhrase = "1)It is the 1exersize.";
		System.out.println(somePhrase);

		Anagram anagramConverter = new Anagram();

		System.out.println(anagramConverter
				.convertsText(somePhrase));
	}

}
