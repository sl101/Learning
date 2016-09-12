package com.smarterama.zhevaha.anagrams;

import java.util.ArrayList;

import com.smarterama.zhevaha.anagrams.CheckLetter;

public class ReversWorlds {

	
	private String startPhrase = "1)It is the 1exersize. 1)Пример номер 1";
	private StringBuilder resultPhrase = new StringBuilder();
	
	ReversWorlds(){
		printAnithing(startPhrase);
		
		reverseOneWorld();
		
		printAnithing(resultPhrase.toString());
		
	}
	
	
	
	private void reverseOneWorld() {
		String [] splitStartArray = startPhrase.split(" ");
		String [] splitStartArrayRevers = new String[splitStartArray.length];
		char [] startPhraseInCharArray = startPhrase.toCharArray();	
		StringBuilder allStartPhraseReverse = new StringBuilder();
		for(int i =0; i<splitStartArray.length;i++){
			splitStartArrayRevers[i] = new StringBuilder(splitStartArray[i]).reverse().toString();
			allStartPhraseReverse.append(splitStartArrayRevers[i]+" ");
		}		
		allStartPhraseReverse.deleteCharAt(allStartPhraseReverse.length()-1);
		char [] charArrayRevers = allStartPhraseReverse.toString().toCharArray();
		CheckLetter checkOneLettr;
		ArrayList<String> arrayListRevers = new ArrayList<String>();
		for(int i =0; i<charArrayRevers.length;i++){
			
			checkOneLettr = new CheckLetter(charArrayRevers[i]);
			if(checkOneLettr.isUnicodNumber()){
				arrayListRevers.add(String.valueOf(charArrayRevers[i]));
				
			}			
			
		}
		
			for(int i =0; i<startPhraseInCharArray.length;i++){
				checkOneLettr = new CheckLetter(startPhraseInCharArray[i]);	
					if(!checkOneLettr.isUnicodNumber()){
				arrayListRevers.add(i,String.valueOf(startPhraseInCharArray[i]));
			}			
		}
		
				for(int i =0; i<arrayListRevers.size();i++){
					resultPhrase.append(arrayListRevers.get(i));
				}
		
	}



	private void printAnithing(String startPhrase2) {

		System.out.println("start example: \t" + startPhrase);
		
	}


}
