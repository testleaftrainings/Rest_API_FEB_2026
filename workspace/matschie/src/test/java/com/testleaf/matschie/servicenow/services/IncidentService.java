package com.testleaf.matschie.servicenow.services;

import org.hamcrest.Matchers;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

public class IncidentService extends Servicenow {
	
	private static final String TABLENAME = "incident";
	
	public IncidentService getRecords() {
		response = apiClient.get(globalConfig(), TABLENAME);
		return this;
	}
	
	public void getRecords(RequestSpecBuilder resRequestSpecBuilder) {
		response = apiClient.get(resRequestSpecBuilder.build(), TABLENAME);
	}
	
	public IncidentService validateResponse(int statusCode, String statusLine, String responseFormat) {
		response.then().assertThat().statusCode(statusCode);
		response.then().assertThat().statusLine(Matchers.containsString(statusLine));
		if(responseFormat.equalsIgnoreCase("JSON")) {
			response.then().assertThat().contentType(ContentType.JSON);
		} else if(responseFormat.equalsIgnoreCase("XML")) {
			response.then().assertThat().contentType(ContentType.XML);
		}
		return this;
	}

}