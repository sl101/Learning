package com.smarterama.zhevaha.IntegerDivision;

import java.util.ArrayList;
import java.util.Random;

public class Division {

	private int dividend;
	private int divider;

	public Division() {
		dividend = new Random().nextInt(100000);
		divider = new Random().nextInt(100);

		// dividend = 2760;
		// divider = 24;

		checkValuesDivision(dividend, divider);
	}

	public int getDividend() {
		return dividend;
	}

	public int getDivider() {
		return divider;
	}

	public String writeDivision(int dividend, int divider) {

		StringBuilder result = new StringBuilder();

		result.append(divide(dividend, divider));

		return result.toString();

	}

	private void checkValuesDivision(int dividendValue, int dividerValue) {

		if (dividendValue < dividerValue) {
			int sorterVariable = dividerValue;
			divider = dividendValue;
			dividend = sorterVariable;
		}
		if (divider == 0) {
			divider = 1;
		}

	}

	private String divide(int dividendValue, int dividerValue) {

		int quotient = dividendValue / dividerValue;

		ArrayList<Integer> numbersDividend = new ArrayList<>();
		numbersDividend = divideOnDigits(dividend);

		ArrayList<Integer> remainders = new ArrayList<Integer>();
		ArrayList<Integer> subtrahends = new ArrayList<Integer>();
		int intermediateDividend = 0;

		for (int i = 0; i < numbersDividend.size(); i++) {
			intermediateDividend = intermediateDividend * 10
					+ numbersDividend.get(i);

			remainders.add(intermediateDividend);
			subtrahends.add((intermediateDividend / divider) * divider);

			intermediateDividend = intermediateDividend % divider;

		}

		if (intermediateDividend != 0) {
			remainders.add(intermediateDividend);
		}

		while (subtrahends.get(0) == 0) {
			subtrahends.remove(0);
			remainders.remove(0);
		}
		int firstRemainder = remainders.get(0);
		remainders.remove(0);

		if (remainders.size() < subtrahends.size())
			remainders.add(0);

		StringBuilder result = new StringBuilder();
		result.append(fillString(1, ' ') + dividend + " |" + divider + "\n");

		for (int i = 0; i < remainders.size(); i++) {

			result.append(fillString(i, ' ') + "-");
			if (i == 0) {
				result.append(fillString(numbersDividend.size() + 1, ' ')
						+ "-----");
			}
			result.append("\n" + fillString(i + 1, ' ') + subtrahends.get(i));
			if (i == 0) {
				result.append(fillString((numbersDividend.size() - String
						.valueOf(subtrahends.get(i)).length()) + 1, ' ')
						+ "|" + quotient);
			}

			result.append("\n"
					+ fillString(i + 1, ' ')
					+ fillString(
							String.valueOf(subtrahends.get(i)).length(),
							'_') + "\n");
			if (firstRemainder - subtrahends.get(i) == 0) {
				result.append(" ");
			}
			result.append(fillString(i + 2, ' ') + remainders.get(i) + "\n");
		}

		return result.toString();
	}

	private String fillString(int count, char filler) {

		StringBuffer result = new StringBuffer(count);

		for (int i = 0; i < count; i++) {
			result.append(filler);
		}

		return result.toString();
	}

	// private ArrayList<Integer> divideOnRanges(int number) {
	//
	// ArrayList<Integer> result = new ArrayList<Integer>();
	//
	// for (int i = 1; i <= number; i *= 10) {
	// int value = number / i;
	// result.add(0, value);
	// }
	//
	// return result;
	// }

	private ArrayList<Integer> divideOnDigits(int number) {

		ArrayList<Integer> result = new ArrayList<Integer>();

		for (int i = 1; i <= number; i *= 10) {
			int value = (number % (i * 10) - number % i) / i;
			result.add(0, value);
		}

		return result;
	}

}
