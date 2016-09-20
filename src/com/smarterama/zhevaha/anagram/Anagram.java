package com.smarterama.zhevaha.anagram;

public class Anagram {


	public String anagramWordsReverse(String startPhrase) {
				
			StringBuilder reverseStartPhrase = getReversePhrase(startPhrase);
			
			StringBuilder resultPhrase = getResultPhrase(reverseStartPhrase.toString(), startPhrase);
					
			return resultPhrase.toString();
		
	}



	private StringBuilder getResultPhrase(String reverseStartPhrase, String startPhrase) {
		
		char [] reversePhrase = reverseStartPhrase.toCharArray();
		StringBuilder methodResult = new StringBuilder();
		for (int i = 0; i < reversePhrase.length; i++) {
			
			if (new Character(reversePhrase[i]).isLetter(reversePhrase[i])) {
				methodResult.append(reversePhrase[i]);
			}							
		}
		
		char[] startCharArray = startPhrase.toCharArray();
		for (int i = 0; i < startPhrase.length(); i++) {			
			//Character character = new Character(startCharArray[i]);
			if (!new Character(reversePhrase[i]).isLetter(startCharArray[i])) {
				methodResult.insert(i, startCharArray[i]);
			}
			
		}
		return methodResult;
	}


	private StringBuilder getReversePhrase(String stringPhrase) {
		
		String [] splitStartPhrase = stringPhrase.split(" ");
		
		StringBuilder allReversePhrase = new StringBuilder();
		for(int i = 0; i <= splitStartPhrase.length-1; i++){
			allReversePhrase.append(new StringBuilder(splitStartPhrase[i]).reverse());
			allReversePhrase.append(" ");
		}
		allReversePhrase.deleteCharAt(allReversePhrase.length()-1);
		return allReversePhrase;
	}

}
