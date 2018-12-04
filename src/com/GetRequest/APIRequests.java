package com.GetRequest;

import static io.restassured.RestAssured.given;
import org.json.simple.JSONObject;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;


public class APIRequests extends BaseClass{	


	public void GetRequest(int count) {
		System.out.println("*******************TestCase-"+count+"*******************");
		RestAssured.baseURI=prop.getProperty("baseURI");
		Response res= given().log().all().
				param("location","-33.8670522,151.1957362").
				param("radius","500").
				param("key","AIzaSyAzqGm9WaUeaO9pY0lMg6k2YoEY7TOraUk").
				when().
				get(prop.getProperty("baseurlresource")).then().assertThat().statusCode(200)
				.and().log().all().contentType(ContentType.JSON).and().extract().response();
		@SuppressWarnings("rawtypes")
		ResponseBody body=res.getBody();                 
		System.out.println("*******************TestCase-"+count+"*********"+"GET PASS"+"*******************");
		System.out.println();
		System.out.println();
		JsonPath jsonPathEvaluator = body.jsonPath();
		String name=jsonPathEvaluator.get("$.results[0].geometry.viewport.northeast.lat");
		Assert.assertEquals(name, "-33.5781409","We have received the correct lattitude for northeast!");
		
	}		

	public void PostRequest(int count) throws Exception {
		System.out.println("*******************TestCase-"+count+"*******************");
		RestAssured.baseURI=prop.getProperty("newbaseURI");
		JSONObject body=readjson(prop.getProperty("pathToTestdata"));
		Response res=given().log().all().
				body(body.toJSONString()).
				when().
				post(prop.getProperty("newbaseURIresource")).then().assertThat().statusCode(200).log().all().
				and().extract().response();			
		//System.out.println(res.asString());
		System.out.println("*******************TestCase-"+count+"*********"+"POST PASS"+"*******************");
		System.out.println();
		System.out.println();
		//JsonPath js= new JsonPath(res.asString());
		//String id=js.get("id");			
		//	testdao.writetoDatabase(id);
		WriteAPI_Response_to_Jsonfile_CEP(res.asString());
	}

	public void PutRequest(int count) throws Exception {		
		JSONObject tempbody=readjson(prop.getProperty("pathToResponse"));
		String id=tempbody.get("id").toString();		
		//TestsDao testdao=new TestsDao();
		System.out.println("*******************TestCase-"+count+"*******************");
		RestAssured.baseURI=prop.getProperty("newbaseURI");
		JSONObject body=readjson(prop.getProperty("pathToTestdata"));
		Response res=given().log().all().
				body(body.toJSONString()).log().all().
				when().
				put(prop.getProperty("puturl")+id).then().assertThat().statusCode(200).and().log().all().
				and().extract().response();			
		//System.out.println(res.asString());
		System.out.println("*******************TestCase-"+count+"*********"+"PUT PASS"+"*******************");
		System.out.println();
		System.out.println();				
		//	testdao.writetoDatabase(id);
		WriteAPI_Response_to_Jsonfile_CEP(res.asString());
	}

	public void DeleteRequest(int count) throws Exception {	
		PostRequest(0);
		JSONObject tempbody=readjson(prop.getProperty("pathToResponse"));
		String id=tempbody.get("id").toString();		
		//TestsDao testdao=new TestsDao();
		//System.out.println("*******************TestCase-"+count+"*******************");
		RestAssured.baseURI=prop.getProperty("newbaseURI");		
		Response res=given().log().all().				
				when().
				delete(prop.getProperty("deleteurl")+id).then().assertThat().statusCode(200).and().log().all().
				and().extract().response();			
		System.out.println(res.asString());
		System.out.println("*******************TestCase-"+count+"*********"+"DELETE PASS"+"*******************");
		System.out.println();
		System.out.println();				
		//	testdao.writetoDatabase(id);
		WriteAPI_Response_to_Jsonfile_CEP(res.asString());
	}	
}