package com.smarterama.zhevaha.anagrams;




public class MainClass {
		
	public static void main(String[] args) {
			
		ReversWorlds reversFraze = new ReversWorlds();
		reversFraze.printAnithing(reversFraze.getStartPhrase());
		reversFraze.reverseOneWorld();
		reversFraze.printAnithing(reversFraze.getResultPhrase().toString());
		
	}

}
