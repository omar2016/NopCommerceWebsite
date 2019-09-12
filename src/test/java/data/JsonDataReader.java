package data;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import bsh.ParseException;

public class JsonDataReader {

	public String firstname , lastname , email ,  password;


		
	
	public void jsonReader () throws ParseException, IOException, org.json.simple.parser.ParseException {
		String filepath = System.getProperty("user.dir")+"/src/test/java/data/userData.json" ;
		File srcfile = new File(filepath);
		JSONParser parser = new JSONParser();
		JSONArray jsonarray = (JSONArray)parser.parse(new FileReader(srcfile));
			for (Object jsonobj : jsonarray) {

				JSONObject  jsonobject =  (JSONObject) jsonobj;
				firstname = (String) jsonobject.get("firstname");
				lastname = (String) jsonobject.get("lastname");
				email = (String) jsonobject.get("email");
				password = (String) jsonobject.get("password");

			}
		}

	}

