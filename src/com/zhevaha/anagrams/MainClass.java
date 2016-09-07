package com.zhevaha.anagrams;

import java.util.ArrayList;


public class MainClass {
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// введем произвольную строку		
		String str = "1)It is the 1exersize. 1)Пример номер 1";
		System.out.println("start example: \t" + str);
		
		// создадим массив из слов строки 
		String [] strSplit = str.split(" ");
		// создаем String массив для аннограмм длиной первоначальной строки
		String [] strSplitRevers = new String[strSplit.length];
		
		//массив char значений элементов строки
		char [] charArray = str.toCharArray();	
	
		// 
		StringBuilder rev = new StringBuilder();
		for(int i =0; i<strSplit.length;i++){
			strSplitRevers[i] = new StringBuilder(strSplit[i]).reverse().toString();
			rev.append(strSplitRevers[i]+" ");
		}
		
		rev.deleteCharAt(rev.length()-1);
		
		// создадим и заполним char массив с анаграмой строки
		char [] charArrayRevers = rev.toString().toCharArray();;
		
		//System.out.println(charArrayRevers);
		
		// для проверки соответсвует ли элемент строки требуемым условиям 
		// создадим класс в котором будет проводиться необходимая проверка 
		// в случае необходимости требования к аннаграмме можно изменить
		
		//ссылка на класс
		CheckLetter chl;
		
		// в ArrayList можно будет добавить значения в необходимые 
		//места увеличивая массив
		ArrayList<String> arrayStr = new ArrayList<String>();
		// заполним ArrayList перевернутыми значениями без элементов 
		//которые необходимо оставить на своих местах
		for(int i =0; i<charArrayRevers.length;i++){
			
			chl = new CheckLetter(charArrayRevers[i]);
			// если проверяемый элемент входит  внеобходимую к 
			// изменению аннаграмму заполняем им ArrayList
			if(chl.isUnicodNumber()){
				//charArrayRevers[i] = charArray[i];
				arrayStr.add(String.valueOf(charArrayRevers[i]));
				
			}			
			
		}
		
		// неизменяемые элементы оставляем на своих местах, добвляя их в ArrayList 
		// по начальному порядковому расположению, сдвигая при этом список вправо 
			for(int i =0; i<charArray.length;i++){
			chl = new CheckLetter(charArray[i]);	
			if(!chl.isUnicodNumber()){
				arrayStr.add(i,String.valueOf(charArray[i]));
			}			
		}
		
		// char массив єлементов приводим к строке и выводим результат
		StringBuilder result = new StringBuilder();
				for(int i =0; i<arrayStr.size();i++){
					result.append(arrayStr.get(i));
				}
		System.out.println("result: \t"+result);
	}

}
