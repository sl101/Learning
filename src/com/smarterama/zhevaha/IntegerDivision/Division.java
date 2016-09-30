package com.smarterama.zhevaha.IntegerDivision;

import java.util.ArrayList;
import java.util.Random;

public class Division {

	private static int dividend;
	private static int divider;
	private static int privateResult;

	public Division() {
		dividend = new Random().nextInt(10000);
		divider = new Random().nextInt(100);
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

		result.append("" + dividend + "\t|" + divider + "\n" + "-"
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
		StringBuilder reminderOfDivident = new StringBuilder();
		StringBuilder reminder = new StringBuilder();
		ArrayList<String> nextDividend = new ArrayList<>();
		ArrayList<String> shortList = new ArrayList<>();
		reminderOfDivident.append(dividendValue);
		do {
			String dividendText = reminderOfDivident.toString();
			reminderOfDivident = new StringBuilder();

			for (int i = 0; i <= dividendText.length(); i++) {
				int numberDigits = Integer.valueOf(dividendText.substring(0,
						i + 1));

				if (numberDigits >= dividerValue) {

					privateResult = numberDigits / dividerValue;
					reminder.append(privateResult);
					int shortlyNumber = privateResult * dividerValue;
					shortList.add(String.valueOf(shortlyNumber));
					reminderOfDivident.append(numberDigits - shortlyNumber);
					reminderOfDivident.append(dividendText.substring(i + 1));
					nextDividend.add(String.valueOf(numberDigits));
					break;
				}

			}
		} while (Integer.valueOf(reminderOfDivident.toString()) > dividerValue);

		result.append(shortList.get(0) + "\t|" + reminder + "\n"
				+ fillString(1, ' ')
				+ fillString(shortList.get(0).length(), '_') + "\n");
		for (int i = 1; i < shortList.size(); i++) {
			result.append(fillString(i, ' ') + nextDividend.get(i) + "\n"
					+ fillString(i, ' ') + "-" + "\n" + fillString(i, ' ')
					+ shortList.get(i)+"\n"+ fillString(i+1, ' ')+"\n" + fillString(i+1, '_') );
		}
		result.append(reminderOfDivident);

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
