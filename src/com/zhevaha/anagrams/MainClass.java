package com.zhevaha.anagrams;

import java.util.ArrayList;


public class MainClass {
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// ������ ������������ ������		
		String str = "1)It is the 1exersize. 1)������ ����� 1";
		System.out.println("start example: \t" + str);
		
		// �������� ������ �� ���� ������ 
		String [] strSplit = str.split(" ");
		// ������� String ������ ��� ��������� ������ �������������� ������
		String [] strSplitRevers = new String[strSplit.length];
		
		//������ char �������� ��������� ������
		char [] charArray = str.toCharArray();	
	
		// 
		StringBuilder rev = new StringBuilder();
		for(int i =0; i<strSplit.length;i++){
			strSplitRevers[i] = new StringBuilder(strSplit[i]).reverse().toString();
			rev.append(strSplitRevers[i]+" ");
		}
		
		rev.deleteCharAt(rev.length()-1);
		
		// �������� � �������� char ������ � ��������� ������
		char [] charArrayRevers = rev.toString().toCharArray();;
		
		//System.out.println(charArrayRevers);
		
		// ��� �������� ������������ �� ������� ������ ��������� �������� 
		// �������� ����� � ������� ����� ����������� ����������� �������� 
		// � ������ ������������� ���������� � ���������� ����� ��������
		
		//������ �� �����
		CheckLetter chl;
		
		// � ArrayList ����� ����� �������� �������� � ����������� 
		//����� ���������� ������
		ArrayList<String> arrayStr = new ArrayList<String>();
		// �������� ArrayList ������������� ���������� ��� ��������� 
		//������� ���������� �������� �� ����� ������
		for(int i =0; i<charArrayRevers.length;i++){
			
			chl = new CheckLetter(charArrayRevers[i]);
			// ���� ����������� ������� ������  ������������ � 
			// ��������� ���������� ��������� �� ArrayList
			if(chl.isUnicodNumber()){
				//charArrayRevers[i] = charArray[i];
				arrayStr.add(String.valueOf(charArrayRevers[i]));
				
			}			
			
		}
		
		// ������������ �������� ��������� �� ����� ������, ������� �� � ArrayList 
		// �� ���������� ����������� ������������, ������� ��� ���� ������ ������ 
			for(int i =0; i<charArray.length;i++){
			chl = new CheckLetter(charArray[i]);	
			if(!chl.isUnicodNumber()){
				arrayStr.add(i,String.valueOf(charArray[i]));
			}			
		}
		
		// char ������ ��������� �������� � ������ � ������� ���������
		StringBuilder result = new StringBuilder();
				for(int i =0; i<arrayStr.size();i++){
					result.append(arrayStr.get(i));
				}
		System.out.println("result: \t"+result);
	}

}
