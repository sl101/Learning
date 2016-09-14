package com.smarterama.zhevaha.anagrams.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.smarterama.zhevaha.anagrams.ReversWorlds;

public class ReverseWorldTest {

	@Test
	public void testReverseOneWorld() {
		ReversWorlds test = new ReversWorlds();
		test.reverseOneWorld();

		String result = test.getResultPhrase().toString();
		assertEquals("1)tI si eht 1ezisrexe.",result);
	}

}
