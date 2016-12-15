package com.foxminded.zhevaha;

public class Main {

	public static void main(String[] args) {

		UniqueSymbolsCounter uniqueSymbolsCounter = new UniqueSymbolsCounter();

		String inputString = "Hello World!";

		System.out
				.println(uniqueSymbolsCounter.countUniqueSymbols(inputString));
	}
}
