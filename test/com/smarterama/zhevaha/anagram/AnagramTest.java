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
		String somePhrase = "1)It is the 1exersize.";
//		String result = test.reversesWords(inputPhrase);
//		assertEquals("tI)1 si eht .ezisrexe1", result);
	}

	@Test
	public void testConvertsText() {
		String somePhrase = "1)It is the 1exersize.";
		String result = test.convertsText(somePhrase);
		assertEquals("1)tI si eht 1ezisrexe.", result);		
	}
	@Test
	@Ignore("not competed")
	public void testConvertsNull() {
		String somePhrase = null;
		String result = test.convertsText(somePhrase);
		assertEquals("Error\n"+"Enter text", result);
		
	}

}