package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {


	static FileInputStream fis = null ;


	public FileInputStream getFileInputStream() {

		String filepath = System.getProperty("user.dir")+"/src/test/java/data/userdata.xlsx";
		File srcpath = new File(filepath);
		try {
			fis = new FileInputStream(srcpath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return fis;
	}

	public Object [][] getExcelData (){

		fis=getFileInputStream();

		XSSFWorkbook wb = null;
		try {
			wb = new XSSFWorkbook(fis);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		XSSFSheet sheet = wb.getSheetAt(0);
		int totalnumberofrow = (sheet.getLastRowNum()+1);
		int totalnumberofcolumn =4;
		String [][] arrayExcelData= new String[totalnumberofrow][totalnumberofcolumn];
		for (int i = 0; i < totalnumberofrow; i++) {

			for (int j = 0; j < totalnumberofcolumn; j++) {
				XSSFRow row = sheet.getRow(i);
				arrayExcelData[i][j]= row.getCell(j).toString();

			}				


			try {
				wb.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   	

		}
		return arrayExcelData;
	}
}
