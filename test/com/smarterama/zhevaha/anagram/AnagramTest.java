package com.smarterama.zhevaha.anagram;

import static org.junit.Assert.*;

import org.junit.Test;

import com.smarterama.zhevaha.anagram.Anagram;

public class AnagramTest {

	Anagram test = new Anagram();


	@Test
	public void testReverseText() {
		String somePhrase = "1)It is the 1exersize.";
		String result = test.reverseAlphabeticalText(somePhrase);
		assertEquals("1)tI si eht 1ezisrexe.", result);
	}

	@Test
	public void testConvertSpace() {
		String somePhrase = " ";
		String result = test.reverseAlphabeticalText(somePhrase);
		assertEquals("Error\n" + "Enter correct text", result);
	}

	@Test
	public void testConvertFirstSpace() {
		String somePhrase = " 1)It is the 1exersize.";
		String result = test.reverseAlphabeticalText(somePhrase);
		assertEquals(" 1)tI si eht 1ezisrexe.", result);
	}
	
	@Test
	public void testConvertNull() {
		String somePhrase = null;
		String result = test.reverseAlphabeticalText(somePhrase);
		assertEquals("Error\n" + "Enter correct text", result);
	}

	@Test
	public void testConvertShortText() {
		String somePhrase = "1";
		String result = test.reverseAlphabeticalText(somePhrase);
		assertEquals("1", result);
	}

	@Test
	public void testConvertEmtyText() {
		String somePhrase = "";
		String result = test.reverseAlphabeticalText(somePhrase);
		assertEquals("Error\n" + "Enter correct text", result);
	}
}