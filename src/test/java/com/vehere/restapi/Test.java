package com.vehere.restapi;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter braces");
		String brace=sc.nextLine();
		
		
		int count=0;
		int i=0;
		while(i!=brace.length()) {
			if(brace.charAt(i)=='{')
			count++;
			
			else if(brace.charAt(i)=='}') {
				count--;
			}
			i++;
		}
		
		if(count==0) {
			System.out.println("Valid");
		}
		else {
			System.out.println("Invalid");
		}
		
		
	}

}
