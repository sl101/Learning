package com.smarterama.zhevaha.anagrams;


public class MainClass {
		
	public static void main(String[] args) {
			
		String startFraze = "1)It is the 1exersize.";
		System.out.println(startFraze);
		
		Anagramma reversFraze = new Anagramma(startFraze);
		
		System.out.println(reversFraze.getResultPhrase());		
	}

}
