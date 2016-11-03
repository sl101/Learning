package com.smarterama.zhevaha.integerDivision;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.smarterama.zhevaha.integerDivision.Division;

public class DivisionTest {

	private static int dividend;
	private static int divider;
	private static Division division;

	@Test
	public void testDivisionWithoutRemainder() {
		dividend = 550;
		divider = 55;
		division = new Division(dividend, divider);

		int result = division.getDividend() % division.getDivider();
		assertTrue(result == 0);
	}
	
	@Test
	public void testDivisionWithRemainder() {
		dividend = 553;
		divider = 55;
		division = new Division(dividend, divider);

		int result = division.getDividend() % division.getDivider();
		assertTrue(result == 3);
	}
	
	@Test
	public void testDividendMoreThanDivider() {

		dividend = 5468;
		divider = 22;
		division = new Division(dividend, divider);
		String result = division.composeDivisionOutput();
		int intResult = division.getDividend() / division.getDivider();

		String expected = " " + 5468 + " |" + 22 + "\n-     -----\n " + 44
				+ "   |" + 5468 / 22 + "\n __\n " + 106 + "\n-\n  " + 88
				+ "\n ___\n  " + 188 + "\n -\n  " + 176 + "\n  ___\n   " + 12
				+ "\n";

		assertEquals(expected, result);
		assertTrue(intResult > 1);

	}

	@Test
	public void testDividerMoreThanDivident() {

		dividend = 20;
		divider = 33;
		division = new Division(dividend, divider);
		String result = division.composeDivisionOutput();
		double intResult = (double) division.getDividend() / division.getDivider();
		
		String expected = " " + 20 + " |" + 33 + "\n-   -----\n  " + 0 + " |"
				+ 20 / 33 + "\n  _\n " + 20 + "\n";

		assertEquals(expected, result);
		assertTrue(intResult > 0 && intResult < 1);

	}
	
	@Test
	public void testDividendEqualsDivider() {
		dividend = 33;
		divider = 33;
		division = new Division(dividend, divider);

		int result = division.getDividend() / division.getDivider();
		assertTrue(result == 1);
	}
	
	@Test(expected = ArithmeticException.class)
	public void testDividerZero() {
		dividend = 100000;
		divider = 0;
		division = new Division(dividend, divider);

		int intResult = division.getDividend() / division.getDivider();
	}
	
	@Test
	public void testDividendZero() {
		dividend = 0;
		divider = 100;
		division = new Division(dividend, divider);

		int intResult = division.getDividend() / division.getDivider();
		assertTrue(intResult == 0);
		
	}


	@Test
	public void testNegativeDividend() {
		dividend = -2016;
		divider = 33;
		division = new Division(dividend, divider);
		String result = division.composeDivisionOutput();

		int intResult = division.getDividend() / division.getDivider();

		String expected = " -" + 2016 + " |" + 33 + "\n-      -----\n  " + 198
				+ "  |" + -61 + "\n ____\n    " + 36 + "\n  "
				+ "-\n    " + 33 + "\n   ___\n     " + 3 + "\n";

		assertEquals(expected, result);
		assertTrue(intResult < 0);
	}
	
	@Test
	public void testNegativeDivider() {
		dividend = 2016;
		divider = -33;
		division = new Division(dividend, divider);
		String result = division.composeDivisionOutput();

		int intResult = division.getDividend() / division.getDivider();

		String expected = " " + 2016 + " |-" + 33 + "\n-     -----\n " + 198
				+ "  |" + -61 + "\n ___\n   " + 36 + "\n  "
				+ "-\n   " + 33 + "\n   __\n    " + 3 + "\n";

		assertEquals(expected, result);
		assertTrue(intResult < 0);
	}
	
	@Test
	public void testNegativeValues() {

		dividend = -5468;
		divider = -22;
		division = new Division(dividend, divider);
		String result = division.composeDivisionOutput();
		int intResult = division.getDividend() / division.getDivider();

		String expected = " -" + 5468 + " |-" + 22 + "\n-      -----\n  " + 44
				+ "   |" + 248 + "\n ___\n  " + 106 + "\n-\n   " + 88
				+ "\n ____\n   " + 188 + "\n -\n   " + 176 + "\n  ____\n    " + 12
				+ "\n";

		assertEquals(expected, result);
		assertTrue(intResult > 1);

	}
}
