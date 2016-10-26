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

		DivisionResults results = computeDivisionResults(dividend, divider);

		return formatResultAsString(results);

	}

	private String formatResultAsString(DivisionResults inputParametrs) {

		ArrayList<Integer> remainders = inputParametrs.getRemainders();
		ArrayList<Integer> subtrahends = inputParametrs.getSubtrahends();

		int length = defineNumberLength(remainders.get(0)) + 1;
		remainders.remove(0);

		if (remainders.size() < subtrahends.size())
			remainders.add(0);

		StringBuilder result = new StringBuilder();

		result.append(" "
				+ dividend
				+ " |"
				+ divider
				+ "\n"
				+ "-"
				+ String.format("%" + (defineNumberLength(dividend) + 1) + "s", " ")
				+ "-----\n"
				+ String.format("%" + length + "d", subtrahends.get(0))
				+ String.format("%"
						+ ((defineNumberLength(dividend) + 2) - (length - 1))
						+ "s", "|")
				+ dividend
				/ divider
				+ "\n"
				+ String.format(
						"%" + length + "s",
						countLengthFiller(
								defineNumberLength(subtrahends.get(0)), "_"))
				+ "\n");
		if (1 == remainders.size())
			result.append(String.format("%" + length + "d",
					remainders.get(0))
					);
		else {
			result.append(String.format("%" + (length +1) + "d",
					remainders.get(0)) );
		}
				
				result.append("\n");

		for (int i = 1; i < remainders.size(); i++) {
			length = length + 1;

			result.append(String.format(
					"%"
							+ ((length + 1) - defineNumberLength(remainders
									.get(i - 1))) + "s", "-\n")
					+ String.format("%" + length + "d", subtrahends.get(i))
					+ "\n"
					+ String.format(
							"%" + length + "s",
							countLengthFiller(
									defineNumberLength(remainders.get(i - 1)),
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

	private DivisionResults computeDivisionResults(int dividendInput,
			int dividerInput) {

		ArrayList<Integer> remainders = new ArrayList<Integer>();
		ArrayList<Integer> subtrahends = new ArrayList<Integer>();

		int dividend = Math.abs(dividendInput);
		int divider = Math.abs(dividerInput);

		int dividendOrder = 1;

		while (dividend / dividendOrder > SCALE)
			dividendOrder *= SCALE;

		int remainder = 0;

		while (dividendOrder > 0) {
			remainder = remainder * SCALE + (dividend / dividendOrder)
					% SCALE;
			if ((dividend / dividendOrder) / SCALE == 1)
				remainder = SCALE;
			remainders.add(remainder);
			subtrahends.add((remainder / divider) * divider);

			remainder = remainder % divider;
			dividendOrder /= SCALE;
		}

		if (remainder != 0) {
			remainders.add(remainder);
		}

		while (subtrahends.get(0) == 0 && subtrahends.size() > 1) {
			subtrahends.remove(0);
			remainders.remove(0);
		}

		DivisionResults methodResult = new DivisionResults();
		methodResult.setRemainders(remainders);
		methodResult.setSubtrahends(subtrahends);
		return methodResult;
	}

	private String countLengthFiller(int count, String filler) {
		StringBuffer result = new StringBuffer(count);
		for (int i = 0; i < count; i++) {
			result.append(filler);
		}

		return result.toString();
	}

	private int defineNumberLength(int number) {
		int numberLength = (number == 0) ? 1 : 0;
		while (number != 0) {
			numberLength++;
			number /= SCALE;
		}
		if (dividend<0) {
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
