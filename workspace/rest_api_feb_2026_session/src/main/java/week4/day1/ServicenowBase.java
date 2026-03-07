package week4.day1;

import static io.restassured.RestAssured.authentication;
import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.basic;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;

import week3.day2.pojos.UpdateIncident;

public class ServicenowBase {
	
	protected static String sys_id;
	protected UpdateIncident updateIncident = new UpdateIncident();
	private static final String BASEURI = "https://dev354951.service-now.com";
	private static final String BASEPATH = "/api/now/table";
	private static final String USERNAME = "admin";
	private static final String PASSWORD = "noJ73hE!aAG!";
	private static final String TABLENAME = "incident";
	protected Map<String, String> pathParams = new HashMap<String, String>();
	
	@BeforeClass
	public void beforeClass() {
		baseURI = BASEURI;
		basePath = BASEPATH;
		authentication = basic(USERNAME, PASSWORD);
		pathParams.put("tableName", TABLENAME);
	}

}