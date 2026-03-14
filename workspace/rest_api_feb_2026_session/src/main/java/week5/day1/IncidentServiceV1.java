package week5.day1;

import java.io.File;

import io.restassured.http.ContentType;

import static org.hamcrest.Matchers.*;

import week3.day2.pojos.CreateIncident;
import week3.day2.pojos.UpdateIncident;

public class IncidentServiceV1 extends ServiceNow {
	
	private static final String TABLENAME = "incident";	
	private static RestAssuredApiClient apiClient = new RestAssuredApiClient();

	public void createIncident(String requestPayload) {
		response = apiClient.get(getGlobalConfig(), TABLENAME);
	}

	public void createIncident(File requestPayload) {
		response = apiClient.post(getGlobalConfig(), TABLENAME, requestPayload);
	}

	public void createIncident(CreateIncident requestPayload) {
		response = apiClient.post(getGlobalConfig(), TABLENAME, requestPayload);

	}

	public void createIncident() {
		response = apiClient.post(getGlobalConfig(), TABLENAME, null);
	}

	public void updateIncident(String sysId, String requestPayload) {
		response = apiClient.put(getGlobalConfig(), TABLENAME+"/"+sysId, requestPayload);
	}

	public void updateIncident(String sysId, File requestPayload) {
		response = apiClient.put(getGlobalConfig(), TABLENAME+"/"+sysId, requestPayload);
	}

	public void updateIncident(String sysId, UpdateIncident requestPayload) {
		response = apiClient.put(getGlobalConfig().pathParam("sys_id", sysId), TABLENAME + "/{sys_id}", requestPayload);
	}

	public void updateIncident(String sysId) {
		response = apiClient.put(getGlobalConfig().pathParam("sys_id", sysId), TABLENAME + "/{sys_id}", null);
	}
	
	public void getAllRecords() {
		response = apiClient.get(getGlobalConfig(), TABLENAME);
	}
	
	public void getARecords(String sysId) {
		response = apiClient.get(getGlobalConfig().pathParam("sys_id", sysId), TABLENAME + "/{sys_id}");
	}
	
	public void deleteExistingRecord(String sysId) {
		response = apiClient.delete(getGlobalConfig().pathParam("sys_id", sysId), TABLENAME + "/{sys_id}");
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