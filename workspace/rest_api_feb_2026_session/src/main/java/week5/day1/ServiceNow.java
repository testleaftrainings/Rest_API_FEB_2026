package week5.day1;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ServiceNow {
	
	private RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
	private static final String BASEURI = "https://dev324941.service-now.com";
	private static final String BASEPATH = "/api/now/table/";
	private static final String USERNAME = "admin";
	private static final String PASSWORD = "/eESj0uC3k+O";
	protected Response response;
	
	protected RequestSpecification getGlobalConfig() {
		return requestSpecBuilder
				.setBaseUri(BASEURI)
				.setBasePath(BASEPATH)
				.setAuth(RestAssured.basic(USERNAME, PASSWORD))
				.build();
	}

}