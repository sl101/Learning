package com.smarterama.zhevaha.anagrams;

public class Anagramma {

	private String resultPhrase;	
	

	public Anagramma(String inputPhrase){
		
		resultPhrase = anagrammaWorldsRevers(inputPhrase);
		
	}	
	
	
	public String getResultPhrase() {
		return resultPhrase;
	}


	private String anagrammaWorldsRevers(String stringPhrase) {
				
			StringBuilder reverseStartPhrase = getReversPhrase(stringPhrase);
			
			StringBuilder resultPhrase = getResultPhrase(reverseStartPhrase.toString(), stringPhrase);
					
			return resultPhrase.toString();
		
	}



	private StringBuilder getResultPhrase(String reverseStartPhrase, String startPhrase) {
		
		char [] reversPhrase = reverseStartPhrase.toCharArray();
		StringBuilder methodResult = new StringBuilder();
		for (int i = 0; i < reversPhrase.length; i++) {
			
			if (new Character(reversPhrase[i]).isLetter(reversPhrase[i])) {
				methodResult.append(reversPhrase[i]);
			}							
		}
		
		char[] startCharArray = startPhrase.toCharArray();
		for (int i = 0; i < startPhrase.length(); i++) {			
			//Character character = new Character(startCharArray[i]);
			if (!new Character(reversPhrase[i]).isLetter(startCharArray[i])) {
				methodResult.insert(i, startCharArray[i]);
			}
			
		}
		return methodResult;
	}


	private StringBuilder getReversPhrase(String stringPhrase) {
		
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
