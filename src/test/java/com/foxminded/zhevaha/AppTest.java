package com.foxminded.zhevaha;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class AppTest {

	private static String inputString;
	private static UniqueSymbols uniqueSymbols;
	private static String[] exampleValues;
	private final static int ITERATIONS_NUMBER = 100;

	@Before
	public void initiateVariables() {
		inputString = "Hello World!";
		uniqueSymbols = new UniqueSymbols();
		exampleValues = new String[] { "Hello", "Hello World!", "World",
				"Hello Foximinded!", "Java", "Foximided", "Example",
				"exampleValues", "world", "Hello", "Hello World!", "World",
				"Hello Foximinded!", "testCapacity", "CacheTest",
				"CacheCapacity" };
	}

	@Test
	public void testCacheCreate() {
		String expected = exampleValues[0];

		assertNotNull(uniqueSymbols.countUniqueSymbols(expected));
	}

	@Test
	public void testCacheCapacity() {
		Map<String, String> cacheExpected = new LinkedHashMap<String, String>();
		for (int i = 0; i < ITERATIONS_NUMBER; i++) {
			Collections.shuffle(Arrays.asList(exampleValues));
			cacheExpected.put(exampleValues[0],
					uniqueSymbols.countUniqueSymbols(exampleValues[0]));
		}

		assertNotNull(cacheExpected.size());
	}

	@Test
	public void testCountUniqueSymbols() {
		String expected = "Hello World!\n{H=1, e=1, l=3, o=2,  =1, W=1, r=1, d=1, !=1}";
		String result = uniqueSymbols.countUniqueSymbols(inputString);

		assertEquals(expected, result);
	}

}