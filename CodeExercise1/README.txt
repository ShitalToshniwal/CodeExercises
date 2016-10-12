This project is created for coding exercise #1, which is as follows:

Q: Explain and discuss your solution
  
While working at the ACME Institute for World Domination, it has been noted that the REST service(http://services.groupkt.com/country/) for Geography indexing isn't all it could be. 
In each of the following cases, you have been asked to test the REST service (including happy and error paths) to create meaningful assertions, add meaningful names, and ensure the test is data driven throughout.
 
[Tests]
 
When the user requests the get “all” resource
Then I should create meaningful assertions
 
When the user requests the get “isocode2” resource
Then I should create meaningful assertions
 
When the user requests the get “isocode3” resource
Then I should create meaningful assertions
 
When the user searches via the “search” resource 
And uses the method “text” 
Then I should create meaningful assertions


DETAILS OF THE IMPLEMENTATION:

Pre-requisites:
1. TestNG plug in (used for creating and running tests)
	Note: If using Eclipse, go to Help > Eclipse MarketPlace, and search for "TestNG". From the search results, install the "TestNG for Eclipse" plug in. 
2. Build the pom.xml so that it downloads all the jar files needed. 
3. Add the following external jars in project Properties > java Build Path:
	a. json-simple-1.1.1.jar from folder “<<workspace>>\.m2\repository\com\googlecode\json-simple\json-simple\1.1.1”
	b. poi-3.15.jar from folder “<<workspace>>\.m2\repository\org\apache\poi\poi\3.15”
	c. poi-contrib-3.7-beta3.jar from “<<workspace>> \.m2\repository\org\apache\poi\poi-contrib\3.7-beta3”
	d. poi-excelant-3.15.jar from folder “<<workspace>> \.m2\repository\org\apache\poi\poi-excelant\3.15”
	e. poi-ooxml-3.15.jar from folder “<<workspace>> \.m2\repository\org\apache\poi\poi-ooxml\3.15”
	f. poi-ooxml-schemas-3.15.jar from folder “<<workspace>> \.m2\repository\org\apache\poi\poi-ooxml-schemas\3.15”
	g. poi-scratchpad-3.15.jar from folder “<<workspace>> \.m2\repository\org\apache\poi\poi-scratchpad\3.15”


Folder structure under src/test/java:
1. Package "apiTests" contains CountryTests.java:
	a. All the tests defined for REST service (http://services.groupkt.com/country/) are in this class. 
	b. Each of these methods will pick up data from corresponding method with @DataProvider annotation, which in turn picks up test cases from the input excel file. 
2. Package "testData"contains:
	a. The input file "TestData.xlsx" listing all the test cases to be executed. Adding more test scenarios is easy.
	b. DataProviderClass.java: contains methods with @DataProvider annotation in TestNG. These methods are used 
3. Package "utility":
	a. Constant.java: contains Constant variables to be used as global variables.
	b. ExcelUtils.java: contains methods to work with Excel document.
	c. Utils.java: contains reusable methods that will be called by other methods in the project.
4. Package "responses: contains expected responses of REST endpoints, in json format.


Test execution:
1. Open CountryTests.java in IDE and run as 'TestNG Test'.
2. Test results: 
	a. The console will list results of executed assertions and their status.
	b. html report of the execution can be found at "<<project path>>\test-output\index.html"
 