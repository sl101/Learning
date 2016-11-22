package com.foxminded.zhevaha;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase {
	
	String [] inputString = new String[]{"Hello World!"};
	StringParser parser = new StringParser(inputString);

	public AppTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	public void testApp() {
		assertTrue(true);
	}

	public void testMain() {

		String expected = "\nHello World!" + "\n \"H\" - 1\n" + " \"e\" - 1\n"
				+ " \"l\" - 3\n" + " \"o\" - 2\n" + " \" \" - 1\n"
				+ " \"W\" - 1\n" + " \"r\" - 1\n" + " \"d\" - 1\n"
				+ " \"!\" - 1";
		String result = parser.composeAnalyzedOutput();
		assertEquals(expected, result);
	}
}
