package com.smarterama.zhevaha.anagram;

import static org.junit.Assert.*;

import org.junit.Test;

import com.smarterama.zhevaha.anagram.Anagram;

public class AnagramTest {

	@Test
	public void testGetReversePhrase() {
		String startPhrase = "1)It is the 1exersize.";
		Anagram test = new Anagram();
		

		String result = test.anagramWordsReverse(startPhrase);
		assertEquals("1)tI si eht 1ezisrexe.", result);
	}

}
