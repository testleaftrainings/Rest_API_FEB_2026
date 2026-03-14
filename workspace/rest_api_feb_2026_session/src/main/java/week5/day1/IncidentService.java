package week5.day1;

import java.io.File;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import week3.day2.pojos.CreateIncident;
import week3.day2.pojos.UpdateIncident;

public class IncidentService extends ServiceNow {
	
	private static final String TABLENAME = "incident";
	private static RequestSpecification request;
	
	public IncidentService() {
		request = given()
		 .spec(getGlobalConfig())
		 .log().all();
	}

	public void createIncident(String requestPayload) {
		response = request
		 .contentType(ContentType.JSON)		 
	   .when()	   
	     .body(requestPayload)
	     .post(TABLENAME);
	}

	public void createIncident(File requestPayload) {
		response = request
		 .contentType(ContentType.JSON)		 
	   .when()
	     .body(requestPayload)
	     .post(TABLENAME);
	}

	public void createIncident(CreateIncident requestPayload) {
		response = request
		 .contentType(ContentType.JSON)		 
	   .when()
	     .body(requestPayload)
	     .post(TABLENAME);

	}

	public void createIncident() {
		response = request
		 .contentType(ContentType.JSON)		 
	   .when()	     
	     .post(TABLENAME);
	}

	public void updateIncident(String sysId, String requestPayload) {
		response = request
		 .contentType(ContentType.JSON)		 
	   .when()
	     .body(requestPayload)
	     .put(TABLENAME+"/"+sysId);
	}

	public void updateIncident(String sysId, File requestPayload) {
		response = request
		 .contentType(ContentType.JSON)		 
	   .when()
	     .body(requestPayload)
	     .put(TABLENAME+"/"+sysId);
	}

	public void updateIncident(String sysId, UpdateIncident requestPayload) {
		response = request
		 .contentType(ContentType.JSON)		
		 .pathParam("sys_id", sysId)
	   .when()
	     .body(requestPayload)
	     .put(TABLENAME+"/{sys_id}");
	}

	public void updateIncident(String sysId) {
		response = request
		 .contentType(ContentType.JSON)		
		 .pathParam("sys_id", sysId)
	   .when()	     
	     .put(TABLENAME+"/{sys_id}");
	}
	
	public void getAllRecords() {
		response = request.when().get(TABLENAME); 
	}
	
	public void getARecords(String sysId) {
		response = request		 	
		 .pathParam("sys_id", sysId)
	   .when()	    
	     .get(TABLENAME+"/{sys_id}");
	}
	
	public void deleteExistingRecord(String sysId) {
		response = request		 	
		 .pathParam("sys_id", sysId)
	   .when()	    
	     .delete(TABLENAME+"/{sys_id}");
	}
	
	public void validateResponse(int statusCode, String statusLine, String responseFormat) {
		response.then().assertThat().statusCode(statusCode);
		response.then().assertThat().statusLine(containsString(statusLine));
		if(responseFormat.equalsIgnoreCase("JSON")) {
			response.then().assertThat().contentType(ContentType.JSON);
		} else if(responseFormat.equalsIgnoreCase("XML")) {
			response.then().assertThat().contentType(ContentType.XML);
		}
	}
	
	public void validateResponse(int statusCode, String statusLine) {
		response.then().assertThat().statusCode(statusCode);
		response.then().assertThat().statusLine(containsString(statusLine));		
	}
	
	public String extractSysIdValue(String jsonPath) {
		return response.then().extract().jsonPath().getString(jsonPath);
	}
	
	public String extractSysIdValue() {
		return response.then().extract().as(CreateIncidentRes.class).getResult().getSysId();
	}
	
	public void validateSysIdValue(String jsonPath, String expectedValue) {
		response.then().assertThat().body(jsonPath, equalTo(expectedValue));
	}
	
	public void validateShortDescriptionValue(String jsonPath, String expectedValue) {
		response.then().assertThat().body(jsonPath, equalTo(expectedValue));
	}
	
	public void validateDescriptionValue(String jsonPath, String expectedValue) {
		response.then().assertThat().body(jsonPath, equalTo(expectedValue));
	}
	
	public void validateCategoryValue(String jsonPath, String expectedValue) {
		response.then().assertThat().body(jsonPath, equalTo(expectedValue));
	}
	
	public void validateResponseBodyValue(String jsonPath, String expectedValue) {
		response.then().assertThat().body(jsonPath, equalTo(expectedValue));
	}

}