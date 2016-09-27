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
	public void testSpace() {
		String somePhrase = " ";
		String result = test.reverseAlphabeticalText(somePhrase);
		assertEquals("Error\n" + "Enter some text", result);
	}

	
	@Test
	
	public void testReverseFirstSpace() {
		String somePhrase = " 1)It is the 1exersize.";
		String result = test.reverseAlphabeticalText(somePhrase);
		assertEquals(" 1)tI si eht 1ezisrexe.", result);
	}
	
	@Test
	public void testIsNull() {
		String somePhrase = null;
		String result = test.reverseAlphabeticalText(somePhrase);
		assertEquals("Error\n" + "Enter some text", result);
	}

	@Test
	public void testReverseShortText() {
		String somePhrase = "1";
		String result = test.reverseAlphabeticalText(somePhrase);
		assertEquals("1", result);
	}

	@Test
	public void testEmtyText() {
		String somePhrase = "";
		String result = test.reverseAlphabeticalText(somePhrase);
		assertEquals("Error\n" + "Enter some text", result);
	}
}