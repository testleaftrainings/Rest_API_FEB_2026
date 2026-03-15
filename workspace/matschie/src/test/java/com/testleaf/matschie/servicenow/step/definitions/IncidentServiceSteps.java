package com.testleaf.matschie.servicenow.step.definitions;

import java.util.List;
import java.util.Map;

import com.testleaf.matschie.servicenow.services.IncidentService;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;

public class IncidentServiceSteps {
	
	private RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
	private IncidentService incidentService = new IncidentService();

	@Given("user should set baseuri as {string} of the servicenow api")
	public void user_should_set_baseuri_as_of_the_servicenow_api(String baseUri) {
		requestSpecBuilder.setBaseUri(baseUri);
	}

	@Given("user should set basepath as {string} of the servicenow api")
	public void user_should_set_basepath_as_of_the_servicenow_api(String basePath) {
		requestSpecBuilder.setBasePath(basePath);
	}

	@Given("user should set basic authenication username as {string} and password as {string}")
	public void user_should_set_basic_authenication_username_as_and_password_as(String username, String password) {
		requestSpecBuilder.setAuth(RestAssured.basic(username, password));
	}

	@When("user should hit get method to retrieve all records from the incident table")
	public void user_should_hit_get_method_to_retrieve_all_records_from_the_incident_table() {
		incidentService.getRecords(requestSpecBuilder);
	}

	@Then("user should see the successfull response with the below expected value")
	public void user_should_see_the_successfull_response_with_the_below_expected_value(DataTable dataTable) {
		List<Map<String, String>> asMaps = dataTable.asMaps();
		for (Map<String, String> map : asMaps) {
			incidentService.validateResponse(Integer.parseInt(map.get("statusCode")), map.get("statusLine"), map.get("responseFormat"));
		}
		
	}

}