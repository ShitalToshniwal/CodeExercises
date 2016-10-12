package utility;

//import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	private static XSSFSheet excelWorkSheet;
	private static XSSFWorkbook excelWorkBook;
	private static XSSFCell cell;
	private static XSSFRow row;
	private static String path;

	//This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method
	public static void setExcelFile(String path,String sheetName) throws Exception {
		ExcelUtils.path = path;

		try {
			// Open the Excel file
			FileInputStream excelFile = new FileInputStream(path);

			// Access the required test data sheet
			excelWorkBook = new XSSFWorkbook(excelFile);
			excelWorkSheet = excelWorkBook.getSheet(sheetName);
		} 
		catch (Exception e){
			throw (e);
		}
	}

	//This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num
	public static String getCellData(int rowNum, int colNum) throws Exception{

		try{
			cell = excelWorkSheet.getRow(rowNum).getCell(colNum);
			String cellData;
			if(cell != null){
				cellData = cell.getStringCellValue();
				return cellData;
			}
			else
				return "";
		}
		catch (Exception e){
			throw (e);
		}

	}

	//This method is to write in the Excel cell, Row num and Col num are the parameters
	@SuppressWarnings({ "static-access", "deprecation" })
	public static void setCellData(String result,  int rowNum, int colNum) throws Exception	{

		try{
			row  = excelWorkSheet.getRow(rowNum);
			cell = row.getCell(colNum, row.RETURN_BLANK_AS_NULL);
			
			if (cell == null) {
				cell = row.createCell(colNum);
				cell.setCellValue(result);
			} 
			else {
				cell.setCellValue(result);
			}

			// Constant variables Test Data path and Test Data file name
			FileOutputStream fileOut = new FileOutputStream(Constant.TESTDATA_FILE_PATH + Constant.TESTDATA_FILE_NAME);
			excelWorkBook.write(fileOut);
			fileOut.flush();
			fileOut.close();

		}
		catch(Exception e){
			throw (e);
		}

	}

	public static String getPath(){
		return path;
	}
	
	public static int getNumOfRows() throws Exception{
		
		try{
			return excelWorkSheet.getLastRowNum();
		}
		catch(Exception e){
			throw(e);
		}
		
	}


	/*public static void createNewResultsExcel(String directotyPath, String fileName, String copyFromExcelFile) throws Exception{
		
		try{
			
			XSSFWorkbook copyExcelWorkbook = new XSSFWorkbook(copyFromExcelFile);
			XSSFSheet copyExcelSheet = copyExcelWorkbook.getSheet("Country");
			
			FileOutputStream out = new FileOutputStream(new File(directotyPath + "\\" + fileName));
			XSSFWorkbook workbook = new XSSFWorkbook(fileName);
			workbook.write(out);
			workbook.createSheet("Country");
			workbook.close();
			
			
		}
		catch(Exception e){
		
			throw(e);
		}
	}*/
}
