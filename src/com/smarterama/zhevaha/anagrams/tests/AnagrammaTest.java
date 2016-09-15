package com.smarterama.zhevaha.anagrams.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.smarterama.zhevaha.anagrams.Anagramma;

public class AnagrammaTest {

	@Test
	public void testgetReversFrase() {
		String startFrase = "1)It is the 1exersize.";
		Anagramma test = new Anagramma(startFrase);
		

		String result = test.getResultPhrase();
		assertEquals("1)tI si eht 1ezisrexe.", result);
	}

}
