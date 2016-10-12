package com.expedia;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class PrintParameterizedTest {
	
	Print objPrintNum = new Print();
	private String input;
	private String expectedResult;
	
	public PrintParameterizedTest(String input, String expectedResult) {
		
		this.input = input;
		this.expectedResult = expectedResult;
	}

	
	@Parameters
	public static Collection<String[]> testConditions(){
		String[][] expectedResult = {{String.valueOf(3), "Hotel"}, 
									{String.valueOf(27), "Hotel"},
									{String.valueOf(99), "Hotel"},
									{String.valueOf(5), "Expedia"}, 
									{String.valueOf(25), "Expedia"},
									{String.valueOf(100), "Expedia"},
									{String.valueOf(15), "HotelExpedia"}, 
									{String.valueOf(45), "HotelExpedia"},
									{String.valueOf(90), "HotelExpedia"},
									{String.valueOf(1), String.valueOf(1)},
									{String.valueOf(49), String.valueOf(49)},
									{String.valueOf(98), String.valueOf(98)},
									{String.valueOf(-1), "false"},
									{String.valueOf(0), "false"},
									{String.valueOf(101), "false"}};
		
		return Arrays.asList(expectedResult);
	}
	

	@SuppressWarnings("static-access")
	@Test
	//Test Data: 
	//Positive tests (multiples of 3): 3, 27, 99
	//Expected result: "Hotel"
	//Positive tests (multiples of 5): 5, 25, 100
	//Expected result: "Expedia"
	//Positive tests (multiples of 3 and 5): 15, 45, 90
	//Expected result: "HotelExpedia"
	//Validations: (not multiples of 3 or 5): 1, 49, 98
	//Expected result: number is printed
	//Validation: Invalid data: -1, 0, 101: code not executed
	public void testAllConditions() {
		
		String actualResult = objPrintNum.printNum(Integer.valueOf(input));
		assertEquals(expectedResult, actualResult);
	}
	
}