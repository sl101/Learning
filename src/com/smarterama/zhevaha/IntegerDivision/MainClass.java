package com.smarterama.zhevaha.IntegerDivision;

import java.util.Random;

public class MainClass {

	private static int dividend, divider;

	public static void main(String[] args) {

//		dividend = new Random().nextInt(99000) + 1000;
//		divider = new Random().nextInt(99) + 1;

		dividend = 5468;
		divider = 22;
//		dividend = 13168;
//		divider = 66;
//		dividend = 21380;
//		divider = 53;
//		dividend = 19740;
//		divider = 5;
		
		Division division = new Division(dividend, divider);

		System.out.println(division.displayProcessDivision());
	}

}
