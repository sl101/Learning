package com.smarterama.zhevaha.IntegerDivision;

import java.util.Random;

public class MainClass {
	
	private static int dividend, divider;

	public static void main(String[] args) {

		dividend = new Random().nextInt(99000)+1000;
		divider = new Random().nextInt(99)+1;
	
		Division division = new Division();

		System.out.println(division.displayProcessDivision(dividend, divider));
	}

	public static int getDividend() {
		return dividend;
	}

	public static int getDivider() {
		return divider;
	}
	
	
	
}
