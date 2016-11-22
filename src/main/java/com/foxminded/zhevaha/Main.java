package com.foxminded.zhevaha;

public class Main {

	public static void main(String[] args) {

		String[] inputStrings = new String[] { "World!", "Hello!",
				"Hello World!", "Hello World!", "Hello!", "Hello World!",
				"World", "Hello Foxminded" };

		StringParser parser = new StringParser(inputStrings);

		System.out.println(parser.composeAnalyzedOutput());
	}
}
