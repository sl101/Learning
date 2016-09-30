package com.smarterama.zhevaha.IntegerDivision;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class DivisionTest {
	Division division = new Division();

	@Test
	public void testDividendNotNull(){
		int dividend = division.getDividend();
		assertNotNull(dividend);
	}
	
	@Test
	public void testDividerNotNull(){
		int divider = division.getDivider();
		assertNotNull(divider);
	}
	
	@Test
	public void testDividerNotZerro(){
		String checkedNumber = division.writeDivision();
		int divider = division.getDivider();		
		assertNotEquals(0, divider);
	}
	
	@Test
	public void testCheckValuesDivision() {
		String checkedNumber = division.writeDivision();
		int dividend = division.getDividend();
		int divider = division.getDivider();
		assertTrue(dividend>0);
		assertTrue(divider>0);
	}

	@Test
	public void testWriteDivisionNotNull() {
		String checkedNumber = division.writeDivision();
		assertNotNull(checkedNumber);
	}

}
