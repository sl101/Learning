package com.smarterama.zhevaha.IntegerDivision;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class DivisionTest {

	private static int dividend;
	private static int divider;
	private static Division division;
	private static String result;

	@BeforeClass
	public static void setUpBeforeClass() {
		dividend = 5468;
		divider = 22;
		division = new Division(dividend, divider);
		result = division.composeDivisionOutput();
	}


	@Test
	public void testDivider() {
		int dividerExpected = divider;
		int dividerResult = division.getDivider();
		assertEquals(dividerExpected, dividerResult);
	}

	@Test
	public void testQuotient() {
		int quotientExpected = dividend / divider;
		int quotientResult = division.getDividend() / division.getDivider();
		assertEquals(quotientExpected, quotientResult);
		
	}

	@Test
	public void testComposeDivisionOutput() {

		String expected = " " + 5468 + " |" + 22
				+ "\n-     -----\n " + 44 + "   |" + 5468 / 22
				+ "\n __\n " + 106 + "\n-\n  " + 88 + "\n ___\n  " + 188
				+ "\n -\n  " + 176 + "\n  ___\n   " + 12 + "\n";

		assertEquals(expected, result);

	}

}
