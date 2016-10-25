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
	private static String result;

	@Test
	public void testDividendZero() {
		dividend = 0;
		divider = 100;		
		division = new Division(dividend, divider);
		
		int quotientResult = division.getDividend() / division.getDivider(); 
		assertTrue("Result is not "+0, quotientResult == 0);
	}
	
	@Test (expected = ArithmeticException.class)
	public void testDividerZero() {
		dividend = 100000;
		divider = 0;		
		division = new Division(dividend, divider);
		
		int quotientResult = division.getDividend() / division.getDivider();
	}
	
	@Test
	public void testDivision() {
		dividend = 100000;
		divider = 100;
		division = new Division(dividend, divider);
		
		int quotientResult = division.getDividend() / division.getDivider(); 
		assertTrue("Result is not "+quotientResult, quotientResult == 1000);
	}
	
	@Test
	public void testValues1() {
		
		dividend = 100000;
		divider = 100;
		division = new Division(dividend, divider);
		result = division.composeDivisionOutput();

		String expected = " " + 100000 + " |" + 100 + "\n-       -----\n " + 100
				+ "    |" + 100000 / 100 + "\n ___\n    " + 0 + "\n   -\n    " + 0
				+ "\n    _\n     " + 0 + "\n    -\n     " + 0 + "\n     _\n      " + 0 
				+"\n     -\n      " + 0 +"\n      _\n      " + 0 +"\n";

		assertEquals(expected, result);

	}
	
	@Test
	public void testValues2() {
		
		dividend = 5468;
		divider = 22;
		division = new Division(dividend, divider);
		result = division.composeDivisionOutput();

		String expected = " " + 5468 + " |" + 22 + "\n-     -----\n " + 44
				+ "   |" + 5468 / 22 + "\n __\n " + 106 + "\n-\n  " + 88
				+ "\n ___\n  " + 188 + "\n -\n  " + 176 + "\n  ___\n   " + 12
				+ "\n";

		assertEquals(expected, result);

	}
	
	@Test
	public void testValues3() {
		
		dividend = 20;
		divider = 33;
		division = new Division(dividend, divider);
		result = division.composeDivisionOutput();

		String expected = " " + 20 + " |" + 33 + "\n-   -----\n  " + 0
				+ " |" + 20 / 33 + "\n  _\n " + 20 + "\n";

		assertEquals(expected, result);

	}
	
	@Test
	public void testValues4() {
		
		dividend = -2016;
		divider = 33;
		division = new Division(dividend, divider);
		result = division.composeDivisionOutput();

		String expected = " -" + 2016 + " |" + 33 + "\n-      -----\n  " + 198
				+ "  |" + -2016 / 33 + "\n ____\n    " + 36 + "\n  "+"-\n    "
				+33+"\n   ___\n     "+3+"\n";

		assertEquals(expected, result);

	}

}
