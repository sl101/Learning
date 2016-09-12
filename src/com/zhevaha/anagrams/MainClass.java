package com.zhevaha.anagrams;

import java.util.ArrayList;


public class MainClass {
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		
		String str = "1)It is the 1exersize. 1)Пример номер 1";
		System.out.println("start example: \t" + str);
		
		String [] strSplit = str.split(" ");
		String [] strSplitRevers = new String[strSplit.length];
		
		char [] charArray = str.toCharArray();	
	
		StringBuilder rev = new StringBuilder();
		for(int i =0; i<strSplit.length;i++){
			strSplitRevers[i] = new StringBuilder(strSplit[i]).reverse().toString();
			rev.append(strSplitRevers[i]+" ");
		}
		
		rev.deleteCharAt(rev.length()-1);
		
		char [] charArrayRevers = rev.toString().toCharArray();;
		
		CheckLetter chl;
		
		ArrayList<String> arrayStr = new ArrayList<String>();
		for(int i =0; i<charArrayRevers.length;i++){
			
			chl = new CheckLetter(charArrayRevers[i]);
			if(chl.isUnicodNumber()){
				arrayStr.add(String.valueOf(charArrayRevers[i]));
				
			}			
			
		}
		
		for(int i =0; i<charArray.length;i++){
			chl = new CheckLetter(charArray[i]);	
			if(!chl.isUnicodNumber()){
				arrayStr.add(i,String.valueOf(charArray[i]));
			}			
		}
		
		StringBuilder result = new StringBuilder();
				for(int i =0; i<arrayStr.size();i++){
					result.append(arrayStr.get(i));
				}
		System.out.println("result: \t"+result);
	}

}
