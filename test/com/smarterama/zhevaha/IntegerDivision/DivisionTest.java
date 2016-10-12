package com.smarterama.zhevaha.IntegerDivision;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class DivisionTest {

	private int dividend;
	private int divider;
	private Division division;
	String result;

	@Before
	public void setUpBeforeClass() {
		dividend = 5468;
		divider = 22;
		division = new Division(dividend, divider);
		result = division.displayProcessDivision();
	}


	@Test
	public void testRemainders() {
		ArrayList<Integer> expecteds = new ArrayList<Integer>();
		expecteds.add(106);
		expecteds.add(188);
		expecteds.add(12);
		ArrayList<Integer> remainders = new ArrayList<Integer>();
		remainders = division.getRemainders();
		for (int i = 0; i < remainders.size(); i++) {
			assertEquals(expecteds, remainders);
		}
	}

	@Test
	public void testSubtrahends() {
		ArrayList<Integer> expecteds = new ArrayList<Integer>();
		expecteds.add(44);
		expecteds.add(88);
		expecteds.add(176);
		ArrayList<Integer> subtrahends = new ArrayList<Integer>();
		subtrahends = division.getSubtrahends();
		for (int i = 0; i < subtrahends.size(); i++) {
			assertEquals(expecteds, subtrahends);
		}
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
	public void testDisplayProcessDivision() {

		String expected = " " + division.getDividend() + " |" + divider
				+ "\n-     -----\n " + 44 + "   |" + dividend / divider
				+ "\n __\n " + 106 + "\n-\n  " + 88 + "\n ___\n  " + 188
				+ "\n -\n  " + 176 + "\n  ___\n   " + 12 + "\n";

		assertEquals(expected, result);

	}

}
