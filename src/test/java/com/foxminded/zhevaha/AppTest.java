package com.foxminded.zhevaha;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

public class AppTest {

	private static String inputString;
	private static UniqueSymbolsCounter uniqueSymbolsCounter;
	private static String[] exampleValues;
	private final static int ITERATIONS_NUMBER = 10000;

	@Before
	public void methodBefore() {
		inputString = "Hello World!";
		uniqueSymbolsCounter = new UniqueSymbolsCounter();
	}

	@Test
	public void testApp() {
		Main mainClass = new Main();
		mainClass.main(new String[] { "test1", "test2" });

		assertTrue(true);
	}

	@Test
	public void testCacheCreate() {
		exampleValues = new String[] { "Hello", "Hello World!", "World",
				"Hello Foximinded!", "Java", "Foximided" };
		for (int i = 0; i < ITERATIONS_NUMBER; i++) {
			Collections.shuffle(Arrays.asList(exampleValues));
			uniqueSymbolsCounter.countUniqueSymbols(exampleValues[0]);
		}

		assertTrue(uniqueSymbolsCounter.getCache().containsKey(inputString));
	}

	@Test
	public void testCacheCapacity() {
		exampleValues = new String[] { "Hello", "Hello World!", "World",
				"Hello Foximinded!", "Java", "Foximided", "Example",
				"exampleValues", "world", "Hello", "Hello World!", "World",
				"Hello Foximinded!", "testCapacity", "CacheTest",
				"CacheCapacity" };
		for (int i = 0; i < ITERATIONS_NUMBER; i++) {
			Collections.shuffle(Arrays.asList(exampleValues));
			uniqueSymbolsCounter.countUniqueSymbols(exampleValues[0]);
		}

		assertTrue(uniqueSymbolsCounter.getCache().size() <= 10);
	}

	@Test
	public void testComposeOutput() {
		String expected = "Hello World!" + "\n \"H\" - 1\n" + " \"e\" - 1\n"
				+ " \"l\" - 3\n" + " \"o\" - 2\n" + " \" \" - 1\n"
				+ " \"W\" - 1\n" + " \"r\" - 1\n" + " \"d\" - 1\n"
				+ " \"!\" - 1";
		String result = uniqueSymbolsCounter.countUniqueSymbols(inputString);

		assertEquals(expected, result);
	}

}