package com.smarterama.zhevaha.anagrams.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.smarterama.zhevaha.anagrams.ReversWorlds;

public class StartFrazeTest {

	@Test
	public void testGetStartFraze() {
		ReversWorlds test = new ReversWorlds();
		String result = test.getStartPhrase();
		assertEquals("1)It is the 1exersize.",result);
	}

}
