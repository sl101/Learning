package com.foxminded.zhevaha;

public class Main {

	public static void main(String[] args) {

		String[] inputStrings = new String[] { "Hello World!", "Hello!",
				"Hello World!" };

		StringParser parser = new StringParser(inputStrings);

		System.out.println(parser.composeAnalyzedOutput());
	}
}
