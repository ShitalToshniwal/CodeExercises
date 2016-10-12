package com.expedia;

public class Print {

	public static String printNum(int number){
		
		String printBuffer = null;
		
		if(number < 1 || number > 100)
			printBuffer = "false";
		else{
			
			if(number % 3 == 0)
				printBuffer = NumberPrinter.HOTEL;
			
			if(number % 5 == 0)
			printBuffer = ((printBuffer == null) ? "" : printBuffer) + NumberPrinter.EXPEDIA; 
			
			if(printBuffer == null)
				printBuffer = String.valueOf(number);
		}
			
		
		return printBuffer;
		
		
		
	/*	if(number % 5 == 0)
		printBuffer = (printBuffer == null) ? 
		

		if(number % 3 == 0 && number % 5 == 0)
			return "HotelExpedia";
		else 
			if(number % 3 == 0)
				return "Hotel";
			else
				if(number % 5 == 0)
					return "Expedia";
				else
					return String.valueOf(number);

		return printBuffer;*/
	}
}
