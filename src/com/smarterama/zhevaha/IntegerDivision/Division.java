package com.smarterama.zhevaha.IntegerDivision;

import java.util.ArrayList;

public class Division {

	private ArrayList<Integer> remainders = new ArrayList<Integer>();
	private ArrayList<Integer> subtrahends = new ArrayList<Integer>();
	private int firstRemainderLength = 0;
	private int dividend, divider;

	public Division() {
	}

	public Division(int dividend, int divider) {
		this.dividend = dividend;
		this.divider = divider;
	}

	public String displayProcessDivision() {

		findFields();

		String result = getResultString();

		return result;
	}

	private String getResultString() {
		StringBuilder result = new StringBuilder();
		int length = getCountsOfDigits(firstRemainderLength) + 1;

		result.append(" "
				+ dividend
				+ " |"
				+ divider
				+ "\n"
				+ "-"
				+ String.format("%" + (getCountsOfDigits(dividend) + 1) + "s",
						" ")
				+ "-----\n"
				+ String.format("%" + length + "d", subtrahends.get(0))
				+ String.format("%"
						+ ((getCountsOfDigits(dividend) + 2) - (length - 1))
						+ "s", "|")
				+ dividend
				/ divider
				+ "\n"
				+ String.format("%" + length + "s",
						fillString(getCountsOfDigits(subtrahends.get(0)), "_"))
				+ "\n"
				+ String.format("%" + (length + 1) + "d", remainders.get(0))
				+ "\n");

		for (int i = 1; i < remainders.size(); i++) {
			length = length + 1;

			result.append(String.format("%"
					+ ((length + 1) - getCountsOfDigits(remainders.get(i - 1)))
					+ "s", "-\n")
					+ String.format("%" + length + "d", subtrahends.get(i))
					+ "\n"
					+ String.format(
							"%" + length + "s",
							fillString(
									getCountsOfDigits(remainders.get(i - 1)),
									"_")) + "\n");
			if (i == remainders.size() - 1)
				result.append(String.format("%" + length + "d",
						remainders.get(i))
						+ "\n");
			else {
				result.append(String.format("%" + (length + 1) + "d",
						remainders.get(i)) + "\n");
			}
		}

		return result.toString();
	}

	private void findFields() {

		int dividendOrder = 1;

		while (dividend / dividendOrder > 10)
			dividendOrder *= 10;

		int remainder = 0;

		while (dividendOrder > 0) {
			remainder = remainder * 10 + (dividend / dividendOrder) % 10;

			remainders.add(remainder);
			subtrahends.add((remainder / divider) * divider);

			remainder = remainder % divider;
			dividendOrder /= 10;
		}

		if (remainder != 0) {
			remainders.add(remainder);
		}

		while (subtrahends.get(0) == 0) {
			subtrahends.remove(0);
			remainders.remove(0);
		}

		firstRemainderLength = remainders.get(0);
		remainders.remove(0);

		if (remainders.size() < subtrahends.size())
			remainders.add(0);
		System.out.println("remainders  " + remainders + "\nsubtrahends  "
				+ subtrahends);

	}

	private String fillString(int count, String filler) {
		StringBuffer result = new StringBuffer(count);

		for (int i = 0; i < count; i++) {
			result.append(filler);
		}

		return result.toString();
	}

	private int getCountsOfDigits(int number) {
		int count = (number == 0) ? 1 : 0;
		while (number != 0) {
			count++;
			number /= 10;
		}
		return count;
	}

	public ArrayList<Integer> getRemainders() {
		return remainders;
	}

	public ArrayList<Integer> getSubtrahends() {
		return subtrahends;
	}

	public int getDividend() {
		return dividend;
	}

	public int getDivider() {
		return divider;
	}

}
