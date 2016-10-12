package com.expedia;

public class NumberPrinter {

	public static final String HOTEL = "Hotel";
	public static final String EXPEDIA = "Expedia";
	
	public static void main(String[] args) {
		
		final int MAX_NUMBER = 100;
		for(int counter = 1; counter <= MAX_NUMBER; counter++){
		
			System.out.println(Print.printNum(counter));
		}

	}

}
