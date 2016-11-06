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
		ArrayList<Integer> fraction = inputParametrs.getQuotientFraction();

		int length = countDigits(remainders.get(0)) + 1;
		remainders.remove(0);

		if (remainders.size() < subtrahends.size()) {
			remainders.add(0);
		}
		StringBuilder result = new StringBuilder();
		result.append(" "
				+ dividend
				+ " |"
				+ divider
				+ "\n"
				+ "-"
				+ String.format("%" + (countDigits(dividend) + 1) + "s", " ")
				+ "-----\n"
				+ String.format("%" + length + "d", subtrahends.get(0))
				+ String.format("%"
						+ ((countDigits(dividend) + 2) - (length - 1)) + "s",
						"|")
				+ dividend
				/ divider
				+ composeQuotient(fraction)
				+ "\n"
				+ String.format("%" + length + "s",
						multiplyString(countDigits(subtrahends.get(0)), "_"))
				+ "\n");
		if (1 == remainders.size()) {
			result.append(String.format("%" + length + "d", remainders.get(0)));
		} else {
			result.append(String.format("%" + (length + 1) + "d",
					remainders.get(0)));
		}

		result.append("\n");

		for (int i = 1; i < remainders.size(); i++) {
			length = length + 1;
			if (subtrahends.get(i) != 0) {
				result.append(String.format("%"
						+ ((length + 1) - countDigits(remainders.get(i - 1)))
						+ "s", "-\n")
						+ String.format("%" + length + "d", subtrahends.get(i))
						+ "\n"
						+ String.format(
								"%" + length + "s",
								multiplyString(
										countDigits(remainders.get(i - 1)), "_"))
						+ "\n");
				if (i == remainders.size() - 1) {
					result.append(String.format("%" + length + "d",
							remainders.get(i))
							+ "\n");
				} else {
					result.append(String.format("%" + (length + 1) + "d",
							remainders.get(i)) + "\n");
				}
			}

		}

		return result.toString();
	}

	private String composeQuotient(ArrayList<Integer> fraction) {
		StringBuilder result = new StringBuilder();
		result.append("");
		if (!fraction.isEmpty()) {
			result.append(".");
			int lastNumber = fraction.get(fraction.size() - 1);
			boolean removeLastNumber = false;
			for (int i = 0; i < fraction.size(); i++) {

				if (fraction.get(i) == lastNumber && i != (fraction.size() - 1)) {
					result.append("(");
					fraction.remove(fraction.size() - 1);
					removeLastNumber = true;
				}
				result.append(fraction.get(i));

				if (removeLastNumber && i == (fraction.size() - 1)) {
					result.append(")");
				}
			}
		}
		return result.toString();
	}

	private DivisionResult divide(int dividendInput, int dividerInput) {

		ArrayList<Integer> remainders = new ArrayList<Integer>();
		ArrayList<Integer> subtrahends = new ArrayList<Integer>();
		ArrayList<Integer> fraction = new ArrayList<Integer>();

		int dividend = Math.abs(dividendInput);
		int divider = Math.abs(dividerInput);

		int dividendOrder = 1;

		while (dividend / dividendOrder > SCALE) {
			dividendOrder *= SCALE;
		}
		int remainder = 0;

		while (dividendOrder > 0) {
			remainder = remainder * SCALE + (dividend / dividendOrder) % SCALE;
			if ((dividend / dividendOrder) / SCALE == 1) {
				remainder = SCALE;
			}
			remainders.add(remainder);
			subtrahends.add((remainder / divider) * divider);
			remainder = remainder % divider;
			dividendOrder /= SCALE;

		}

		dividendOrder = SCALE;
		int count = 0;

		while (remainder != 0 && count <= SCALE) {

			count++;
			remainder = remainder * SCALE;
			remainders.add(remainder);
			subtrahends.add((remainder / divider) * divider);
			fraction.add(remainder / divider);
			remainder = remainder % divider;

			if (fraction.contains(remainder * SCALE / divider)) {
				fraction.add(remainder * SCALE / divider);
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
		result.setQuotientFraction(fraction);
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
		if (dividend < 0) {
			numberLength++;
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
