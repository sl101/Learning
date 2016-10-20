package com.smarterama.zhevaha.IntegerDivision;

import java.util.ArrayList;

public class Division {

	private int dividend, divider;

	public Division(int dividend, int divider) {
		this.dividend = dividend;
		this.divider = divider;
	}

	
	public String composeDivisionOutput() {
		
		IntermediateListsOfDivision intermediateParameters = computeIntermediateParameters(dividend, divider);

		return formatResultAsString(intermediateParameters);
		
	}

	private String formatResultAsString(IntermediateListsOfDivision inputParametrs) {
		
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
				+ String.format("%" + (defineNumberLength(dividend) + 1) + "s",
						" ")
				+ "-----\n"
				+ String.format("%" + length + "d", subtrahends.get(0))
				+ String.format("%"
						+ ((defineNumberLength(dividend) + 2) - (length - 1))
						+ "s", "|")
				+ dividend
				/ divider
				+ "\n"
				+ String.format("%" + length + "s",
						fillGaps(defineNumberLength(subtrahends.get(0)), "_"))
				+ "\n"
				+ String.format("%" + (length + 1) + "d", remainders.get(0))
				+ "\n");

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
							fillGaps(defineNumberLength(remainders.get(i - 1)),
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

	private IntermediateListsOfDivision computeIntermediateParameters(int dividend, int divider) {

		ArrayList<Integer> remainders = new ArrayList<Integer>();
		ArrayList<Integer> subtrahends = new ArrayList<Integer>();
			
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
		
		IntermediateListsOfDivision  methodResult = new IntermediateListsOfDivision();
		methodResult.setRemainders(remainders);
		methodResult.setSubtrahends(subtrahends);
		return methodResult;
	}

	private String fillGaps(int count, String filler) {
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
			number /= 10;
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
