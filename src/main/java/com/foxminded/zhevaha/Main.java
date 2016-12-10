package com.foxminded.zhevaha;

public class Main {

	public static void main(String[] args) {

		UniqueSymbolsCounter uniqueCounter = new UniqueSymbolsCounter();

		String inputString = "Hello World!";

		System.out.println(uniqueCounter.countUniqueSymbols(inputString));
	}
}
