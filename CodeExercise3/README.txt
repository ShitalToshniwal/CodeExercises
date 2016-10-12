This project is created for coding exercise #3, which is as follows:

Q: Write a program that prints the numbers from 1 to 100. 
But for multiples of three print “Hotel” instead of the number and for the multiples of five print “Expedia”. 
For numbers which are multiples of both three and five print “HotelExpedia”.


DETAILS OF THE IMPLEMENTATION:

Pre-requisites:
1. JUnit plug in (used for creating and running tests)


Folder structure under src/main/java:
1. Package "com.expedia":
	a. NumberPrinter.java: contains the main() method
	b. Print.java: contains the logic to print numbers
2. Package src/test/java:
	a. PrintParameterizedTest.java: JUnit test to define and run tests using parameterized data.
	b. PrintTest.java: JUnit test to define and run tests based on separate test conditions.

	
Project execution:
1. Open the project in IDE
2. Select the project and Run As -> Java Application (main() method is inside NumberPrinter.java class)


JUnit Test execution:
1. Open PrintParameterized.java in IDE and run as 'JUnit Test'.
2. Test results: JUnit tab will list the status of executed assertions.
3. Open PrintTest.java in IDE and run as 'JUnit Test'.
4. Test results: JUnit tab will list the status of executed assertions.