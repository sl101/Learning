package com.smarterama.zhevaha.anagrams;

public class CheckLetter {

	private boolean unicodNumber;
	
	public CheckLetter(int cod) {
		unicodNumber = ifTheLetter(cod);
	}

	private boolean ifTheLetter(int i){
		boolean isLetter = false;
		if (i>=65 && i<=90 || i>=97 && i<=122 || i>=1040 && i<=1103 
				|| i==1105 || i==1111 || i==1025 ||i==32){
			isLetter = true;
		}
		return isLetter;
	}

	public boolean isUnicodNumber() {
		
		return unicodNumber;
	}

	public void setUnicodNumber(boolean unicodNumber) {
		this.unicodNumber = unicodNumber;
	}
	
	
	
	
}
