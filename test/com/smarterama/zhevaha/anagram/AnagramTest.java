package com.smarterama.zhevaha.anagram;

import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;

import com.smarterama.zhevaha.anagram.Anagram;

public class AnagramTest {

	Anagram test = new Anagram();
	
	@Test
	@Ignore("not competed")
	public void testReturnsSymbolsOnOriginalPosition() {
		String inputPhrase = "1)It is the 1exersize.";
		String result = test.reversesLettersOfWordInPhrase(inputPhrase);
		assertEquals("tI)1 si eht .ezisrexe1", result);
	}


	@Test
	public void testReversesLettersOfWordInPhrase() {
		String startPhrase = "1)It is the 1exersize.";
		String result = test.reversesLettersOfWordInPhrase(startPhrase);
		assertEquals("1)tI si eht 1ezisrexe.", result);
		
	}

}
