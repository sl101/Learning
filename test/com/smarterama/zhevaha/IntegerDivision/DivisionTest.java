package com.smarterama.zhevaha.IntegerDivision;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class DivisionTest {
	MainClass main = new MainClass();
	Division division = new Division();
	

	@Test
	public void testDivisionNotNull() {		
		int dividend = main.getDividend();		
		assertFalse(dividend>0);
	}

	@Test
	public void testDividerNotNull() {		
		int divider = main.getDivider();
		assertFalse(divider>0);
	}
	
//	@Test
//	public void testDividerNotNull() {
//		int divider = division.getDivider();
//		assertNotNull(divider);
//	}
//
//	@Test
//	public void testDividerNotZerro() {
//		String checkedNumber = division.displayProcessDivision(division.getDividend(),division.getDivider());
//		int divider = division.getDivider();
//		assertNotEquals(0, divider);
//	}
//
//	@Test
//	public void testCheckValuesDivision() {
//		String checkedNumber = division.displayProcessDivision(division.getDividend(),division.getDivider());
//		int dividend = division.getDividend();
//		int divider = division.getDivider();
//		assertTrue(dividend > 0);
//		assertTrue(divider > 0);
//	}
//
//	@Test
//	public void testWriteDivisionNotNull() {
//		String checkedNumber = division.displayProcessDivision(division.getDividend(),division.getDivider()
//				);
//		assertNotNull(checkedNumber);
//	}
//
//	@Test
//	@Ignore
//	public void testFindNumbersQuotient() {
//		
//		int dividend = 4598;
//		int divider = 25;
//		int quotient = dividend/divider;
//		ArrayList<Integer> expecteds = new ArrayList<Integer>();
//		expecteds.add(1);
//		expecteds.add(8);
//		expecteds.add(3);
//		ArrayList<Integer> numbersQuotient = division.divideOnDigits(quotient);
//		assertSame(expecteds.get(1), numbersQuotient.get(1));
//	}
}
