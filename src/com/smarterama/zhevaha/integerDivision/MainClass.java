package com.smarterama.zhevaha.integerDivision;

import java.util.Random;

public class MainClass {

	private static int dividend, divider;

	public static void main(String[] args) {

		dividend = new Random().nextInt(99000) + 1000;
		divider = new Random().nextInt(99) + 1;

		Division division = new Division(dividend, divider);

		System.out.println(division.composeDivisionOutput());
	}

}
