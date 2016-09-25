package com.smarterama.zhevaha.anagram;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import com.smarterama.zhevaha.anagram.Anagram;

public class AnagramTest {

	Anagram test = new Anagram();

	@Test
	@Ignore("not competed")
	public void testReversesWords() {
<<<<<<< HEAD
		String somePhrase = "1)It is the 1exersize.";
		// String result = test.reversesWords(inputPhrase);
		// assertEquals("tI)1 si eht .ezisrexe1", result);
=======
		String inputPhrase = "1)It is the 1exersize.";
//		String result = test.reversesWords(inputPhrase);
//		assertEquals("tI)1 si eht .ezisrexe1", result);
>>>>>>> 6e310583c06c8521587b309cdca33ec8de97e366
	}

	@Test
<<<<<<< HEAD
	public void testConvertsText() {
		String somePhrase = "1)It is the 1exersize.";
		String result = test.convertsText(somePhrase);
=======
	public void testReversesLettersOfWordInPhrase() {
		String startPhrase = "1)It is the 1exersize.";
		String result = test.convertsText(startPhrase);
>>>>>>> 6e310583c06c8521587b309cdca33ec8de97e366
		assertEquals("1)tI si eht 1ezisrexe.", result);
	}

	@Test
	public void testConvertsSpace() {
		String somePhrase = " ";
		String result = test.convertsText(somePhrase);
		assertEquals("Error\n" + "Enter correct text", result);
	}

	@Test
	public void testConvertsNull() {
		String somePhrase = null;
		String result = test.convertsText(somePhrase);
		assertEquals("Error\n" + "Enter correct text", result);
	}

	@Test
	public void testConvertsShortText() {
		String somePhrase = "1";
		String result = test.convertsText(somePhrase);
		assertEquals("1", result);
	}

}