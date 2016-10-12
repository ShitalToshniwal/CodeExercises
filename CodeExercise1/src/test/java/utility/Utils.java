package utility;

import java.util.ArrayList;
import java.util.List;

public class Utils {
	
public static List<String[]> createListFromExcelForFunction(String listDistinguisher) throws Exception{
		
		List<String[]> rowList_all = new ArrayList<String[]>();
		List<String[]> rowList_iso2code= new ArrayList<String[]>();
		List<String[]> rowList_iso3code = new ArrayList<String[]>();
		List<String[]> rowList_search = new ArrayList<String[]>();
		String testCaseName, testCaseParamValue;
		int rowCount = ExcelUtils.getNumOfRows();
		
		try{
			for(int i = Constant.ROW_FIRSTTESTCASE; i<rowCount; i++) {

				String testCaseFunction = ExcelUtils.getCellData(i, Constant.COL_FUNCTION);
				//System.out.println("Row " + i + " function: " + testCaseFunction);

				switch(testCaseFunction)
				{
				case "all":
					testCaseName = ExcelUtils.getCellData(i, Constant.COL_TCNAME);
					testCaseParamValue = "NA";
					rowList_all.add((testCaseName+","+testCaseParamValue+",").split(","));
					break;
				case "iso2code":
					testCaseName = ExcelUtils.getCellData(i, Constant.COL_TCNAME);
					testCaseParamValue = ExcelUtils.getCellData(i, Constant.COL_DATA);
					rowList_iso2code.add((testCaseName+","+testCaseParamValue+",").split(","));
					break;
				case "iso3code":
					testCaseName = ExcelUtils.getCellData(i, Constant.COL_TCNAME);
					testCaseParamValue = ExcelUtils.getCellData(i, Constant.COL_DATA);
					rowList_iso3code.add((testCaseName+","+testCaseParamValue+",").split(","));
					break;
				case "search":
					testCaseName = ExcelUtils.getCellData(i, Constant.COL_TCNAME);
					testCaseParamValue = ExcelUtils.getCellData(i, Constant.COL_DATA);
					rowList_search.add((testCaseName+","+testCaseParamValue+",").split(","));
					break;
				default:
					
				}

			}

		}
		catch(Exception e){
			throw(e);
		}
		
		switch(listDistinguisher){
		case "all":
			return rowList_all;
		case "iso2code":
			return rowList_iso2code;
		case "iso3code":
			return rowList_iso3code;
		case "search":
			return rowList_search;
		default:
			return null;
		}
		
	}

	
		
}
