package com.smarterama.zhevaha.anagram;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import com.smarterama.zhevaha.anagram.Anagram;

public class AnagramTest {

	Anagram test = new Anagram();

	@Test
	@Ignore("not competed")
	public void testReverseWords() {
		String somePhrase = "1)It is the 1exersize.";
		// String result = test.reversesWords(inputPhrase);
		// assertEquals("tI)1 si eht .ezisrexe1", result);
	}

	@Test
	public void testConvertText() {
		String somePhrase = "1)It is the 1exersize.";
		String result = test.convertText(somePhrase);
		assertEquals("1)tI si eht 1ezisrexe.", result);
	}

	@Test
	public void testConvertSpace() {
		String somePhrase = " ";
		String result = test.convertText(somePhrase);
		assertEquals("Error\n" + "Enter correct text", result);
	}

	@Test
	public void testConvertFirstSpace() {
		String somePhrase = " 1)It is the 1exersize.";
		String result = test.convertText(somePhrase);
		assertEquals(" 1)tI si eht 1ezisrexe.", result);
	}
	
	@Test
	public void testConvertNull() {
		String somePhrase = null;
		String result = test.convertText(somePhrase);
		assertEquals("Error\n" + "Enter correct text", result);
	}

	@Test
	public void testConvertShortText() {
		String somePhrase = "1";
		String result = test.convertText(somePhrase);
		assertEquals("1", result);
	}

	@Test
	public void testConvertEmtyText() {
		String somePhrase = "";
		String result = test.convertText(somePhrase);
		assertEquals("Error\n" + "Enter correct text", result);
	}
}