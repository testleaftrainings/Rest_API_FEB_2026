package com.testleaf.matschie.servicenow.services;

import com.testleaf.matschie.rest.assured.api.client.RestAssuredApiClient;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Servicenow {
	
	private RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
    protected RestAssuredApiClient apiClient = new RestAssuredApiClient();
	protected Response response;
	
	protected RequestSpecification globalConfig() {
		return requestSpecBuilder.setBaseUri("https://dev324941.service-now.com")
				                 .setBasePath("/api/now/table/")
				                 .setAuth(RestAssured.basic("admin", "/eESj0uC3k+O"))
				                 .build();
	}

}