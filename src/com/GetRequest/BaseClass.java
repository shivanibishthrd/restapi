package com.GetRequest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class BaseClass {
	public  FileInputStream fin;
	public  Properties prop;
	public  Properties prop2;
	public JSONParser parser;
	public JSONObject jsonObject;
	static int count=1;

	public BaseClass(){
		try {	
			System.out.println(System.getProperty("user.dir"));
			BufferedReader br = new BufferedReader(new FileReader(
					System.getProperty("user.dir")+"/src/com/Resource/Property.properties"));
			BufferedReader br2 = new BufferedReader(new FileReader(
					System.getProperty("user.dir")+"/src/com/Resource/Querries.properties"));
			prop=new Properties();
			prop2=new Properties();
			prop.load(br);	
			prop2.load(br2);			
			//TestsDao.intDB();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception {		
		APIRequests obj=new APIRequests();

		  obj.GetRequest(count++);	
		//	obj.PostRequest(count++);
		//	obj.PutRequest(count++);
		//obj.DeleteRequest(count++);
	}

	public JSONObject readjson(String path){
		parser=new JSONParser();
		try {
			Object object = parser
					.parse(new FileReader(System.getProperty("user.dir")+path));
			// /DemoRest
			jsonObject = (JSONObject)object;
		}catch (IOException|ParseException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	//	public void writejson(JsonPath js){
	//		 try (FileWriter file = new FileWriter("f:\\test.json")) {
	//
	//	            file.write(js);
	//	            file.flush();
	//
	//	        } catch (IOException e) {
	//	            e.printStackTrace();
	//	        }

	public static void WriteAPI_Response_to_Jsonfile_CEP(String API_ResponseData) throws Exception {	
		BufferedWriter buffer=null;
		try {
			File file = new File(System.getProperty("user.dir")+"/src/com/Resource/Response.json");
			if (file.exists()) {
				file.delete();
			} else {
				file.createNewFile();
			}
			FileWriter writer = new FileWriter(file);
			buffer = new BufferedWriter(writer);
			buffer.write(API_ResponseData);
		}catch(FileNotFoundException e) {
			e.getStackTrace();
		}finally {
			buffer.close();
		}
	}
}
