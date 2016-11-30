package com.foxminded.zhevaha;

public class Main {

	public static void main(String[] args) {

		String[] defaultValues = new String[] { "Hello", "Hello World!",
				"World", "Hello Foximinded!", "Java", "Foximided" };

		StringsParser parser = new StringsParser();

		System.out.println(parser.composeOutput(defaultValues));
	}
}
