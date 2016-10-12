package apiTests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.*;

import java.io.FileReader;
import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
import testData.DataProviderClass;
import utility.*;

public class CountryTests {

	String path = Constant.TESTDATA_FILE_PATH + "\\src\\test\\java\\testData\\" + Constant.TESTDATA_FILE_NAME;
	static ArrayList<String> names = new ArrayList<String>();
	static ArrayList<String> alpha2_codes = new ArrayList<String>();
	static ArrayList<String> alpha3_codes = new ArrayList<String>();
	static ArrayList<String> messages = new ArrayList<String>();
	
	@BeforeTest
	public void getURL() throws Exception {

		try{
			
			ExcelUtils.setExcelFile(path, "Country");	
			RestAssured.baseURI = ExcelUtils.getCellData(0, 1);
			RestAssured.basePath = "/country";
		}
		catch(Exception e){
			throw(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	@BeforeTest
	public void getDataFromSampleJSONResponse() throws Exception{
		
		try{
			
			JSONParser parser = new JSONParser();
			/*JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(Constant.TESTDATA_FILE_PATH + "\\src\\test\\java\\responses\\" + Constant.JSONRESPONSE_FILE_NAME));
			for(Object o: jsonArray){
				JSONObject RestResp = (JSONObject) o;
				String x = (String) RestResp.get("RestResponse");
				System.out.println(x);
			}*/
			Object obj = parser.parse(new FileReader(Constant.TESTDATA_FILE_PATH + "\\src\\test\\java\\responses\\" + Constant.JSONRESPONSE_FILE_NAME));
			JSONObject jsonObj = (JSONObject) obj;
			
			JSONArray msg = (JSONArray) jsonObj.get("messages");
			messages.addAll(msg);
			/*Iterator<String> iterator = msg.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}*/
			
			JSONArray res = (JSONArray) jsonObj.get("result");
			for(int i = 0;  i <res.size(); i++){
				JSONObject block = (JSONObject) res.get(i);
				String name = (String) block.get("name");
				names.add(name);
				String alpha2_code = (String) block.get("alpha2_code");
				alpha2_codes.add(alpha2_code);
				String alpha3_code = (String) block.get("alpha3_code");
				alpha3_codes.add(alpha3_code);
			}
		}
		catch(Exception e){
			throw(e);
		}
		
	}

	@Test(dataProvider = "GetAll", dataProviderClass = DataProviderClass.class)
	public static void getAllCountries(String testCaseName, String testCaseParameter) throws Exception{

		try{

			if(testCaseName != null){
				
				Response resp = when().
						get("/get/all").
						then().
						contentType(ContentType.JSON).
						extract().
						response();

				System.out.println("Test case name: " + testCaseName);
				
				//assert that response code is 200
				Assert.assertTrue(resp.getStatusCode()== 200);
				System.out.print("Assertion: Status code is 200. ");
				System.out.println(resp.getStatusCode()== 200 ? "PASS" : "FAIL");
				
				//assert the message in response as compared to expected response
				ArrayList<String> actualMessages = resp.path("RestResponse.messages");
				Assert.assertTrue(messages.equals(actualMessages));
				System.out.print("Assertion: message in response is as expected. ");
				System.out.println(messages.equals(actualMessages) ? "PASS" : "FAIL");
				
				//assert that country names match as compared with expected response
				ArrayList<String> actualNames = resp.path("RestResponse.result.name");
				//Collections.sort(actualNames);
				//Collections.sort(names);
				Assert.assertTrue(actualNames.equals(names));
				System.out.print("Assertion: country names match with expected response. ");
				System.out.println(actualNames.equals(names) ? "PASS" : "FAIL");
				
				//assert that alpha2_codes match as compared with expected response
				ArrayList<String> actual2Codes = resp.path("RestResponse.result.alpha2_code");
				//Collections.sort(actual2Codes);
				//Collections.sort(alpha2_codes);
				Assert.assertTrue(actual2Codes.equals(alpha2_codes));
				System.out.print("Assertion: alpha2_codes match with expected response. ");
				System.out.println(actual2Codes.equals(alpha2_codes) ? "PASS" : "FAIL");
					
				//assert that alpha3_codes as compared with expected response
				ArrayList<String> actual3Codes = resp.path("RestResponse.result.alpha3_code");
				//Collections.sort(actual3Codes);
				//Collections.sort(alpha3_codes);
				Assert.assertTrue(actual3Codes.equals(alpha3_codes));
				System.out.print("Assertion: alpha3_codes match with expected response. ");
				System.out.println(actual3Codes.equals(alpha3_codes) ? "PASS" : "FAIL");

			}

		}
		catch(Exception e){
			throw(e);
		}
	}


	@Test(dataProvider = "GetIso2code", dataProviderClass = DataProviderClass.class)
	public static void getCountryByIso2Code(String testCaseName, String testCaseParameter) throws Exception{
		try{
			
			if(testCaseName != null){

				Response resp = given().
						pathParam("alpha2_code", testCaseParameter).
						when(). 
						get("/get/iso2code/{alpha2_code}");
				
				System.out.println();
				System.out.println("Test case name: " + testCaseName + ". Input parameter: " + testCaseParameter);
				
				//assert that response code is 200
				Assert.assertTrue(resp.getStatusCode()== 200);
				System.out.print("Assertion: Status code is 200. ");
				System.out.println(resp.getStatusCode()== 200 ? "PASS" : "FAIL");
				
				//assert message when a matching country is found or not
				String extra = "not ";
				if(alpha2_codes.contains(testCaseParameter))
					extra = "";
				System.out.print("Assertion: Message in response is as expected when country is " + extra + "found. ");
				ArrayList<String> actaulMessage = resp.path("RestResponse.messages");
				String expectedMessage = ((alpha2_codes.contains(testCaseParameter)) ? "Country found matching code " : "No matching country found for requested code ") + "[" + testCaseParameter + "].";				
				Assert.assertTrue(expectedMessage.equals(actaulMessage.get(1)));
				System.out.println(expectedMessage.equals(actaulMessage.get(1)) ? "PASS" : "FAIL");
				System.out.println("--Actual message: " + actaulMessage.get(1));
				
				
				//assert data if country is found
				if(alpha2_codes.contains(testCaseParameter)){
					
					System.out.print("Assertion: Data for country code is correct. ");
					String actualName = resp.path("RestResponse.result.name");
					String expectedName = names.get(alpha2_codes.indexOf(testCaseParameter));
					String actualIso2code = resp.path("RestResponse.result.alpha2_code");
					String expectedIso2code = alpha2_codes.get(alpha2_codes.indexOf(testCaseParameter));
					String actualIso3code = resp.path("RestResponse.result.alpha3_code");
					String expectedIso3code = alpha3_codes.get(alpha2_codes.indexOf(testCaseParameter));
					Assert.assertTrue(actualName.equals(expectedName) && actualIso2code.equals(expectedIso2code) && actualIso3code.equals(expectedIso3code));
					System.out.println(actualName.equals(expectedName) && actualIso2code.equals(expectedIso2code) && actualIso3code.equals(expectedIso3code) ? "PASS" : "FAIL");
					
				}
			}


		}
		catch(Exception e){
			throw(e);
		}

	}

	@Test(dataProvider = "GetIso3code", dataProviderClass = DataProviderClass.class)
	public static void getCountryByIso3Code(String testCaseName, String testCaseParameter) throws Exception{

		try{	

			if(testCaseName != null){
				Response resp = given().
						pathParam("alpha3_code", testCaseParameter).
						when(). 
						get("/get/iso3code/{alpha3_code}");

				System.out.println();
				System.out.println("Test case name: " + testCaseName + ". Input parameter: " + testCaseParameter);

				//assert that response code is 200
				Assert.assertTrue(resp.getStatusCode()== 200);
				System.out.print("Assertion: Status code is 200. ");
				System.out.println(resp.getStatusCode()== 200 ? "PASS" : "FAIL");
				
				//assert message when a matching country is found or not
				String extra = "not ";
				if(alpha3_codes.contains(testCaseParameter))
					extra = "";
				System.out.print("Assertion: Message in response is as expected when country is " + extra + "found. ");
				ArrayList<String> actaulMessage = resp.path("RestResponse.messages");
				String expectedMessage = ((alpha3_codes.contains(testCaseParameter)) ? "Country found matching code " : "No matching country found for requested code ") + "[" + testCaseParameter + "].";				
				Assert.assertTrue(expectedMessage.equals(actaulMessage.get(1)));
				System.out.println(expectedMessage.equals(actaulMessage.get(1)) ? "PASS" : "FAIL");
				System.out.println("--Actual message: " + actaulMessage.get(1));
				
				//assert data if country is found
				if(alpha3_codes.contains(testCaseParameter)){
					
					System.out.print("Assertion: Data for country code is correct. ");
					String actualName = resp.path("RestResponse.result.name");
					String expectedName = names.get(alpha3_codes.indexOf(testCaseParameter));
					String actualIso2code = resp.path("RestResponse.result.alpha2_code");
					String expectedIso2code = alpha2_codes.get(alpha3_codes.indexOf(testCaseParameter));
					String actualIso3code = resp.path("RestResponse.result.alpha3_code");
					String expectedIso3code = alpha3_codes.get(alpha3_codes.indexOf(testCaseParameter));
					Assert.assertTrue(actualName.equals(expectedName) && actualIso2code.equals(expectedIso2code) && actualIso3code.equals(expectedIso3code));
					System.out.println(actualName.equals(expectedName) && actualIso2code.equals(expectedIso2code) && actualIso3code.equals(expectedIso3code) ? "PASS" : "FAIL");
					
				}
			}

		}
		catch(Exception e){
			throw(e);
		}

	}

	@Test(dataProvider = "Search", dataProviderClass = DataProviderClass.class)
	//@Test(dataProvider = "SearchMock", dataProviderClass = DataProviderClass.class)
	public static void getCountryBySearch(String testCaseName, String testCaseParameter) throws Exception{

		try{
			
			if(testCaseName != null){
				
				Response resp = given().
						param("text", testCaseParameter).
						when(). 
						get("/search");
	
				System.out.println();
				System.out.println("Test case name: " + testCaseName + ". Input parameter: " + testCaseParameter);

				//assert that response code is 200
				Assert.assertTrue(resp.getStatusCode()== 200);
				System.out.print("Assertion: Status code is 200. ");
				System.out.println(resp.getStatusCode()== 200 ? "PASS" : "FAIL");
				
				
				//assert message when a matching country is found or not
				System.out.print("Assertion: Message in response is as expected for search. ");
				ArrayList<String> actaulMessage = resp.path("RestResponse.messages");
				ArrayList<Integer> searchIndex = new ArrayList<Integer>();
				
				int count = 0;
				for(int i = 0; i < names.size(); i++){
					if((names.get(i).toLowerCase()).contains(testCaseParameter.toLowerCase())){
						//System.out.println("i = " + i + ". name = " + names.get(i));
						count++;
						searchIndex.add(i);
					}
						
					else
						if((alpha2_codes.get(i)).toLowerCase().contains(testCaseParameter.toLowerCase())){
							
							//System.out.println("i = " + i + ". alpha2_code = " + alpha2_codes.get(i));
							count++;
							searchIndex.add(i);
						}
						else
							if((alpha3_codes.get(i)).toLowerCase().contains(testCaseParameter.toLowerCase())){
								//System.out.println("i = " + i + ". alpha3_code = " + alpha3_codes.get(i));
								count++;
								searchIndex.add(i);
							}
						
				}
				String expectedMessage = (count > 0) ? "Total [" + count +"] records found." : "No matching country found for requested code [" + testCaseParameter + "].";
				Assert.assertTrue(expectedMessage.equals(actaulMessage.get(1)));
				System.out.println(expectedMessage.equals(actaulMessage.get(1)) ? "PASS" : "FAIL");
				System.out.println("--Actual message: " + (actaulMessage.get(1)));
				
				//assert data for all the countries listed
				if(count > 0){
					
					ArrayList<String> actualName = resp.path("RestResponse.result.name");
					ArrayList<String> actualIso2code = resp.path("RestResponse.result.alpha2_code");
					ArrayList<String> actualIso3code = resp.path("RestResponse.result.alpha3_code");
					int actualResultCounter = 0;
					
					System.out.print("Assertion: Data results for country code " + testCaseParameter + " are correct. ");
					for(int j = 0; j < searchIndex.size()-1; j++){
						//System.out.println(searchIndex.get(j) + ". " + names.get(searchIndex.get(j)));
						//System.out.println(actualName.get(j) + ", " + actualIso2code.get(j) + ", " + actualIso3code.get(j));
						
						String expectedName = names.get(searchIndex.get(j));
						String expectedIso2code = alpha2_codes.get(searchIndex.get(j));
						String expectedIso3code = alpha3_codes.get(searchIndex.get(j));
						if(actualName.get(j).equals(expectedName) && actualIso2code.get(j).equals(expectedIso2code) && actualIso3code.get(j).equals(expectedIso3code))
							actualResultCounter++;
						//System.out.println(actualName.get(j).equals(expectedName) && actualIso2code.get(j).equals(expectedIso2code) && actualIso3code.get(j).equals(expectedIso3code) ? "PASS" : "FAIL");
							
					}
					Assert.assertTrue(actualResultCounter > 0);
					System.out.println(actualResultCounter > 0 ? "PASS" : "FAIL");
				}
				
					
				
			}

		}
		catch(Exception e){
			throw(e);
		}

	}
}
