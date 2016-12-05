package com.foxminded.zhevaha;

public class Main {

	private final static String HELLO_WORlD = "Hello World!";

	public static void main(String[] args) {

		StringsParser parser = new StringsParser();

		System.out.println(parser.composeOutput(HELLO_WORlD));
	}
}
