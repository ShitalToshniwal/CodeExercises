package com.expedia;

import static org.junit.Assert.*;

import org.junit.Test;

@SuppressWarnings({ "static-access"})
public class PrintTest {
	
	Print objPrintNum = new Print();
	
	@Test
	//Test Data: 
	//Positive tests (multiples of 3): 3, 27, 99
	//Expected result: "Hotel"
	public void testMultiplesOf3() {
		
		assertEquals("Hotel", objPrintNum.printNum(3));
		assertEquals("Hotel", objPrintNum.printNum(27));
		assertEquals("Hotel", objPrintNum.printNum(99));

	}
	
	@Test
	//Test Data: 5, 25, 100, 2, 52, 97
	//Positive tests (multiples of 5): 5, 25, 100
	//Expected result: "Expedia"
	public void testMultiplesOf5(){
		
		assertEquals("Expedia", objPrintNum.printNum(5));
		assertEquals("Expedia", objPrintNum.printNum(25));
		assertEquals("Expedia", objPrintNum.printNum(100));
	}
	
	@Test
	//Test Data: 15, 45, 90
	//Positive tests (multiples of 5 and 5): 15, 45, 90
	//Expected result: "HotelExpedia"
	//Validations: (not multiples of 3 or 5): 1, 49, 98
	//Expected result: number is printed
	public void testMultipleOf3And5(){
	
		assertEquals("HotelExpedia", objPrintNum.printNum(15));
		assertEquals("HotelExpedia", objPrintNum.printNum(45));
		assertEquals("HotelExpedia", objPrintNum.printNum(90));
		assertEquals(String.valueOf(1), objPrintNum.printNum(1));
		assertEquals(String.valueOf(49), objPrintNum.printNum(49));
		assertEquals(String.valueOf(98), objPrintNum.printNum(98));
		
	}
	

	@Test
	//Test Data: -1, 0, 101
	public void testInvalidData(){
		
		assertFalse(Boolean.valueOf(objPrintNum.printNum(-1)));
		assertFalse(Boolean.valueOf(objPrintNum.printNum(0)));
		assertFalse(Boolean.valueOf(objPrintNum.printNum(101)));
	}

}
