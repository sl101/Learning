package com.foxminded.zhevaha;

public class Main {

	public static void main(String[] args) {

		String[] defaultValues = new String[] { "H", "e", "l", "l", "o", "!" };

		StringsParser parser = new StringsParser();

		System.out.println(parser.composeOutput(defaultValues));
	}
}
