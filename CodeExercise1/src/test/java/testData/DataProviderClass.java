package testData;


//import java.util.Arrays;
import java.util.List;

import org.testng.annotations.DataProvider;
import utility.Utils;

public class DataProviderClass {

	@DataProvider
	public Object[][] GetAll() throws Exception{

		try{
			List<String[]> rowList = Utils.createListFromExcelForFunction("all");

			Object [][] testCase = new Object[rowList.size()][];
			rowList.toArray(testCase);

			return testCase;
		}
		catch(Exception e){
			throw(e);
		}



	}

	@DataProvider
	public Object[][] GetIso2code() throws Exception{

		try{

			List<String[]> rowList = Utils.createListFromExcelForFunction("iso2code");

			Object [][] testCase = new Object[rowList.size()][];
			rowList.toArray(testCase);

			return testCase;
		}
		catch(Exception e){
			throw(e);
		}

	}
	
	@DataProvider
	public Object[][] GetIso3code() throws Exception{

		try{

			List<String[]> rowList = Utils.createListFromExcelForFunction("iso3code");

			Object [][] testCase = new Object[rowList.size()][];
			rowList.toArray(testCase);

			return testCase;
		}
		catch(Exception e){
			throw(e);
		}

	}
	
	@DataProvider
	public Object[][] Search() throws Exception{

		try{

			List<String[]> rowList = Utils.createListFromExcelForFunction("search");

			Object [][] testCase = new Object[rowList.size()][];
			rowList.toArray(testCase);

			return testCase;
		}
		catch(Exception e){
			throw(e);
		}

	}
	
	@DataProvider
	public Object[][] SearchMock() throws Exception{

		Object [][] testCase = new Object[3][2];
		
		testCase[0][0] = "TC_M1";
		testCase[0][1] = "in";
		
		testCase[1][0] = "TC_M2";
		testCase[1][1] = "IND";
		
		testCase[2][0] = "TC_M3";
		testCase[2][1] = "uu";
			
		return testCase;

	}


}
