package com.smarterama.zhevaha.IntegerDivision;

import java.util.ArrayList;
import java.util.Random;

public class Division {

	private static int dividend;
	private static int divider;

	public Division() {
		dividend = new Random().nextInt(1000);
		divider = new Random().nextInt(10);
	}

	public int getDividend() {
		return dividend;
	}

	public int getDivider() {
		return divider;
	}

	public String writeDivision() {

		StringBuilder result = new StringBuilder();

		checkValuesDivision(dividend, divider);

		result.append(fillString(1, ' ') + dividend + "\t|" + divider + "\n"
				+ "-" + fillString(String.valueOf(dividend).length(), ' ')
				+ "\t-----\n");
		result.append(divide(dividend, divider));
		return result.toString();
	}

	private void checkValuesDivision(int dividendValue, int dividerValue) {

		if (dividendValue < dividerValue) {
			int result = dividerValue;
			divider = dividendValue;
			dividend = result;
		}
		if (divider == 0) {
			divider = 1;
		}

	}

	private String divide(int dividendValue, int dividerValue) {

		StringBuilder result = new StringBuilder();
		StringBuilder intermediateDividend = new StringBuilder();
		StringBuilder quotient = new StringBuilder();
		ArrayList<String> nextDividend = new ArrayList<>();
		ArrayList<String> subtrahend = new ArrayList<>();
		intermediateDividend.append(dividendValue);
		do {
			String dividendText = intermediateDividend.toString();
			intermediateDividend = new StringBuilder();

			for (int i = 0; i <= dividendText.length(); i++) {
				int numberDigits = Integer.valueOf(dividendText.substring(0,
						i + 1));

				if (numberDigits >= dividerValue) {

					int intermediateQuotient = numberDigits / dividerValue;
					quotient.append(intermediateQuotient);
					int intermediateSubtrahend = intermediateQuotient
							* dividerValue;
					subtrahend.add(String.valueOf(intermediateSubtrahend));
					intermediateDividend.append(numberDigits
							- intermediateSubtrahend);
					intermediateDividend.append(dividendText.substring(i + 1));
					nextDividend.add(String.valueOf(numberDigits));
					break;
				}

			}
		} while (Integer.valueOf(intermediateDividend.toString()) > dividerValue);

		result.append(fillString(1, ' ') + subtrahend.get(0)
				+ fillString(String.valueOf(dividend).length(), ' ') + "\t|"
				+ quotient + "\n" + fillString(1, ' ')
				+ fillString(nextDividend.get(0).length() + 1, '_') + "\n");
		for (int i = 1; i < subtrahend.size(); i++) {
			result.append(fillString(i + 1, ' ') + nextDividend.get(i) + "\n"
					+ fillString(i, ' ') + "-" + "\n" + fillString(i + 1, ' ')
					+ subtrahend.get(i) + "\n" + fillString(i + 1, ' ')
					+ fillString(subtrahend.get(i).length(), '_') + "\n");
		}
		result.append(fillString(nextDividend.size() + 1, ' ')
				+ intermediateDividend);

		return result.toString();
	}

	private String fillString(int count, char filler) {

		StringBuffer result = new StringBuffer(count);

		for (int i = 0; i < count; i++) {
			result.append(filler);
		}

		return result.toString();
	}

}
