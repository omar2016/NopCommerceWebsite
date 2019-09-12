package data;

//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//import org.openqa.selenium.json.Json;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
import com.google.gson.JsonParser;

import bsh.ParseException;

public class JsonReaderWithdarray{


	public static Object[][] getJsonData ( String typedata , int row, int column) throws ParseException, IOException, org.json.simple.parser.ParseException {

		String filepath = System.getProperty("user.dir")+"/src/test/java/data/userData.json" ;
		JsonParser parser = new JsonParser();
		JsonObject jsonobj = ((JsonElement) parser.parse(new FileReader(filepath))).getAsJsonObject();
		JsonArray jsonarray = (JsonArray) jsonobj.get( typedata);
		return searchJsonElement(jsonarray, row, column);
				
	}
	public static Object[][] searchJsonElement(JsonArray jsonarray ,  int row , int column ){

		Object [][] matrix =new Object [row][column];
		int i =0;
		int j=0;
		for (JsonElement jsonelement : jsonarray) {


			for (Map.Entry<String,JsonElement> entry : jsonelement.getAsJsonObject().entrySet()) {

				matrix[i][j] = entry.getValue().toString().replace("\"", "  ");
				j++;
			}
			i++;
			j=0;
		
		}return matrix;
		
	}


}

