package com.foxminded.zhevaha.collections;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.foxminded.zhevaha.collections.UniqueSymbols;

public class AppTest {

	private static UniqueSymbols uniqueSymbols;

	@Before
	public void initiateVariables() {
		uniqueSymbols = new UniqueSymbols();
	}

	@Test
	public void testSpeedCaching() {
		String example = "Write an application that takes a string and returns the number of unique characters in the string. It is expected that a string with the same character sequence may be passed several times to the method. Since the counting operation can be time consuming, the method should cache the results, so that when the method is given a string previously encountered, it will simply retrieve the stored result. Use collections and maps where appropriate.";
		String secondExample = example;
		String[] examples = { example, secondExample };
		long cacheWritingTime = 0;
		long cacheReadTime = 0;
		for (int i = 0; i < examples.length; i++) {
			if (i == 0) {
				cacheWritingTime = count(examples, i);
			} else {
				cacheReadTime = count(examples, i);
			}
		}
		assertTrue(cacheReadTime < cacheWritingTime);
	}

	private long count(String[] equalExamples, int i) {
		long result;
		result = System.currentTimeMillis();
		uniqueSymbols.count(equalExamples[i]);
		result = System.currentTimeMillis() - result;
		return result;
	}

	@Test
	public void testCount() {
		String input = "Hello World!";
		String expected = "{H=1, e=1, l=3, o=2,  =1, W=1, r=1, d=1, !=1}";
		String result = uniqueSymbols.count(input);

		assertEquals(expected, result);
	}

}