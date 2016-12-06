package com.foxminded.zhevaha;

public class Main {

	private final static String[] exampleValues = new String[] { "Hello",
			"Hello World!", "World", "Hello Foximinded!", "Java", "Foximided" };

	public static void main(String[] args) {

		StringsParser parser = new StringsParser();

		System.out.println(parser.composeOutput(exampleValues));
	}
}
