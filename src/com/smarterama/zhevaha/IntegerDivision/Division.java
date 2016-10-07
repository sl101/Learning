package com.smarterama.zhevaha.IntegerDivision;

import java.util.ArrayList;

public class Division {
	
	private ArrayList<Integer> numbersDividend = new ArrayList<Integer>();
	private ArrayList<Integer> remainders = new ArrayList<Integer>();
	private ArrayList<Integer> subtrahends = new ArrayList<Integer>();
//	private int firstRemainder;
	private StringBuilder result = new StringBuilder();

	public String displayProcessDivision(int dividend, int divider) {

		numbersDividend = splitByNumbers(dividend);	
		
		findVariables(divider);
				
		
		result.append(fillString(1, ' ') + dividend + " |" + divider + "\n");

		for (int i = 0; i < remainders.size(); i++) {

			result.append(fillString(i, ' ') + "-");
			
			if (i == 0) {
				result.append(fillString(i, ' ') 
						+ fillString(numbersDividend.size() + 1, ' ')
						+ "-----");
			}
			
			result.append("\n" + fillString(i + 1, ' ') 
						+ subtrahends.get(i));
			
			if (i == 0) {
				result.append(fillString((numbersDividend.size() - String
						.valueOf(subtrahends.get(i)).length()) + 1, ' ')
						+ "|" 
						+ dividend / divider);
			}

			result.append("\n"
					+ fillString(i+1 , ' ')
					+ fillString(String.valueOf(subtrahends.get(i)).length(), '_') 
					+ "\n");
			
//			if (firstRemainder - subtrahends.get(i) == 0) {
//				result.append(" ");
//			}
			
			result.append(fillString(i + 2, ' ') + remainders.get(i) + "\n");
		}

		return result.toString();
	}

	private void findVariables(int divider) {
		int remainder = 0;

		for (int i = 0; i < numbersDividend.size(); i++) {
			remainder = remainder * 10
					+ numbersDividend.get(i);

			remainders.add(remainder);
			subtrahends.add((remainder / divider) * divider);

			remainder = remainder % divider;
		}

		if (remainder != 0) {
			remainders.add(remainder);
		}

		while (subtrahends.get(0) == 0) {
			subtrahends.remove(0);
			remainders.remove(0);
		}

//		firstRemainder = remainders.get(0);
		remainders.remove(0);

		if (remainders.size() < subtrahends.size())
			remainders.add(0);
		
	}

	private String fillString(int count, char filler) {

		StringBuffer result = new StringBuffer(count);

		for (int i = 0; i < count; i++) {
			result.append(filler);
		}

		return result.toString();
	}


	private ArrayList<Integer> splitByNumbers(int number) {

		ArrayList<Integer> result = new ArrayList<Integer>();

		for (int i = 1; i <= number; i *= 10) {
			int value = (number % (i * 10) - number % i) / i;
			result.add(0, value);
		}

		return result;
	}

}
