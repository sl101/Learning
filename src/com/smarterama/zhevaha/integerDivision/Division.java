package com.smarterama.zhevaha.integerDivision;

import java.util.ArrayList;

public class Division {

	private final int SCALE = 10;
	private int dividend, divider;

	public Division(int dividend, int divider) {
		this.dividend = dividend;
		this.divider = divider;
	}

	public String composeDivisionOutput() {

		DivisionResult results = divide(dividend, divider);

		return formatResultAsString(results);

	}

	private String formatResultAsString(DivisionResult inputParametrs) {

		ArrayList<Integer> remainders = inputParametrs.getRemainders();
		ArrayList<Integer> subtrahends = inputParametrs.getSubtrahends();
		ArrayList<Integer> remaindersOfFraction = inputParametrs
				.getRemaindersOfFraction();

		int numberOfDigits = countDigits(remainders.get(0)) + 1;

		int alignmentBeforeDivider = countDigits(dividend);

		if (countDigits(subtrahends.get(0)) > countDigits(dividend)) {
			alignmentBeforeDivider = countDigits(subtrahends.get(0));
		}
		remainders.remove(0);

		if (remainders.size() < subtrahends.size()) {
			remainders.add(0);
		}
		StringBuilder result = new StringBuilder();
		if (dividend < 0) {
			result.append("-");
		} else {
			result.append(" ");
		}
		result.append(String.format("%-" + alignmentBeforeDivider + "s", ""
				+ Math.abs(dividend))
				+ " |"
				+ divider
				+ "\n"
				+ "-"
				+ String.format("%" + alignmentBeforeDivider + "s", " ")
				+ " -----\n "
				+ String.format("%-" + alignmentBeforeDivider + "s", ""
						+ subtrahends.get(0))
				+ " |"
				+ dividend
				/ divider
				+ composeFraction(remaindersOfFraction)
				+ "\n"
				+ String.format("%" + numberOfDigits + "s",
						multiplyString(countDigits(subtrahends.get(0)), "_"))
				+ "\n");
		if (1 == remainders.size()) {
			result.append(String.format("%" + numberOfDigits + "d",
					remainders.get(0)));
		} else {
			result.append(String.format("%" + (numberOfDigits + 1) + "d",
					remainders.get(0)));
		}

		result.append("\n");

		for (int i = 1; i < remainders.size(); i++) {
			numberOfDigits = numberOfDigits + 1;
			if (subtrahends.get(i) != 0) {
				result.append(String.format(
						"%"
								+ ((numberOfDigits + 1) - countDigits(remainders
										.get(i - 1))) + "s", "-\n")
						+ String.format("%" + numberOfDigits + "d",
								subtrahends.get(i))
						+ "\n"
						+ String.format(
								"%" + numberOfDigits + "s",
								multiplyString(
										countDigits(remainders.get(i - 1)), "_"))
						+ "\n");
				if (i == remainders.size() - 1) {
					result.append(String.format("%" + numberOfDigits + "d",
							remainders.get(i)) + "\n");
				} else {
					result.append(String.format("%" + (numberOfDigits + 1)
							+ "d", remainders.get(i))
							+ "\n");
				}
			}

		}

		return result.toString();
	}

	private String composeFraction(ArrayList<Integer> remaindersOfFraction) {
		StringBuilder result = new StringBuilder();
		result.append("");
		if (!remaindersOfFraction.isEmpty()) {
			result.append(".");
			int lastNumber = remaindersOfFraction.get(remaindersOfFraction
					.size() - 1);
			boolean removeLastNumber = false;
			for (int i = 0; i < remaindersOfFraction.size(); i++) {

				if (remaindersOfFraction.get(i) == lastNumber
						&& i != (remaindersOfFraction.size() - 1)) {
					result.append("(");
					remaindersOfFraction
							.remove(remaindersOfFraction.size() - 1);
					removeLastNumber = true;
				}
				result.append(remaindersOfFraction.get(i) / divider);

				if (removeLastNumber && i == (remaindersOfFraction.size() - 1)) {
					result.append(")");
				}
			}
		}
		return result.toString();
	}

	private DivisionResult divide(int dividendInput, int dividerInput) {

		ArrayList<Integer> remainders = new ArrayList<Integer>();
		ArrayList<Integer> subtrahends = new ArrayList<Integer>();
		ArrayList<Integer> remaindersOfFraction = new ArrayList<Integer>();

		int dividend = Math.abs(dividendInput);
		int divider = Math.abs(dividerInput);

		int dividendOrder = 1;

		while (dividend / dividendOrder >= 1) {
			dividendOrder *= SCALE;
		}
		int remainder = 0;

		while (dividendOrder > 0) {
			remainder = remainder * SCALE + (dividend / dividendOrder) % SCALE;
			if (((dividend / dividendOrder) / SCALE) * SCALE == 1) {
				remainder = SCALE;
			}
			remainders.add(remainder);
			subtrahends.add((remainder / divider) * divider);
			remainder = remainder % divider;
			dividendOrder /= SCALE;

		}
		int count = 1;

		while (remainder != 0 && count <= SCALE) {

			count++;
			remainder = remainder * SCALE;
			remainders.add(remainder);
			subtrahends.add((remainder / divider) * divider);
			remaindersOfFraction.add(remainder);
			remainder = remainder % divider;

			if (remaindersOfFraction.contains(remainder * SCALE)) {
				remaindersOfFraction.add(remainder * SCALE);
				break;
			}
		}

		if (remainder != 0) {
			remainders.add(remainder);
		}

		while (subtrahends.get(0) == 0 && subtrahends.size() > 1) {
			subtrahends.remove(0);
			remainders.remove(0);
		}

		DivisionResult result = new DivisionResult();
		result.setRemainders(remainders);
		result.setSubtrahends(subtrahends);
		result.setRemaindersOfFraction(remaindersOfFraction);
		return result;
	}

	private String multiplyString(int count, String filler) {
		StringBuffer result = new StringBuffer(count);
		for (int i = 0; i < count; i++) {
			result.append(filler);
		}

		return result.toString();
	}

	private int countDigits(int number) {
		int numberLength = (number == 0) ? 1 : 0;
		while (number != 0) {
			numberLength++;
			number /= SCALE;
		}
		return numberLength;
	}

	public int getDividend() {
		return dividend;
	}

	public int getDivider() {
		return divider;
	}

}
