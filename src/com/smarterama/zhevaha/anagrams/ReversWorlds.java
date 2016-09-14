package com.smarterama.zhevaha.anagrams;

import java.util.ArrayList;

import com.smarterama.zhevaha.anagrams.CheckLetter;

public class ReversWorlds {

	
	private String startPhrase;
	private StringBuilder resultPhrase;
	
	public ReversWorlds(){
		
		startPhrase = "1)It is the 1exersize.";
		
	}
	
	
	
	public String getStartPhrase() {
		return startPhrase;
	}



	public void setStartPhrase(String startPhrase) {
		this.startPhrase = startPhrase;
	}



	public StringBuilder getResultPhrase() {
		return resultPhrase;
	}



	public void setResultPhrase(StringBuilder resultPhrase) {
		this.resultPhrase = resultPhrase;
	}



	public void reverseOneWorld() {
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
		
			resultPhrase = new StringBuilder();
				for(int i =0; i<arrayListRevers.size();i++){
					resultPhrase.append(arrayListRevers.get(i));
				}
		
	}



	public void printAnithing(String somePhrase) {

		System.out.println(somePhrase);
		
	}


}
