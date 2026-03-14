package steps.def;

import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import week3.day2.pojos.CreateIncident;

public class IncidentServiceSteps {

	RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
	Response response;
	CreateIncident createIncident = new CreateIncident();

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
		response = RestAssured.given().spec(requestSpecBuilder.build()).get("/incident");
	}

	@Then("user should see the status code should be {string}")
	public void user_should_see_the_status_code_should_be(String statusCode) {
		response.then().assertThat().statusCode(Integer.parseInt(statusCode));
	}

	@Then("user should see the status line should be {string}")
	public void user_should_see_the_status_line_should_be(String statusLine) {
		response.then().assertThat().statusLine(Matchers.containsString(statusLine));
	}

	@Then("user should get response in the {string} format")
	public void user_should_get_response_in_the_format(String responseFromat) {
		if (responseFromat.equalsIgnoreCase("JSON")) {
			response.then().assertThat().contentType(ContentType.JSON);
		} else if (responseFromat.equalsIgnoreCase("XML")) {
			response.then().assertThat().contentType(ContentType.XML);
		}
	}
	
	@Given("user should set header key as {string} and value as {string}")
	public void user_should_set_header_key_as_and_value_as(String key, String value) {
	    requestSpecBuilder.addHeader(key, value);
	}
	
	@Given("user should enter the short description as {string} in the request body")
	public void user_should_enter_the_short_description_as_in_the_request_body(String shortDescription) {
		createIncident.setShortDescription(shortDescription);
	}
	
	@Given("user should enter the description as {string} in the request body")
	public void user_should_enter_the_description_as_in_the_request_body(String description) {
	    createIncident.setDescription(description);
	}
	@Given("user should enter the category as {string} in the request body")
	public void user_should_enter_the_category_as_in_the_request_body(String category) {
	    createIncident.setCategory(category);
	}

	@When("user should hit post method to create new record in the incident table")
	public void user_should_hit_post_method_to_create_new_record_in_the_incident_table() {
		response = RestAssured.given().spec(requestSpecBuilder.build()).post("/incident");
	}
	
	@Then("user should see the successfull response with the below expected value")
	public void user_should_see_the_successfull_response_with_the_below_expected_value(DataTable dataTable) {
		List<Map<String, String>> asMaps = dataTable.asMaps();
		for (Map<String, String> map : asMaps) {
			response.then().assertThat().statusCode(Integer.parseInt(map.get("statusCode")));
			response.then().assertThat().statusLine(Matchers.containsString(map.get("statusLine")));
			if (map.get("responseFormat").equalsIgnoreCase("JSON")) {
				response.then().assertThat().contentType(ContentType.JSON);
			} else if (map.get("responseFormat").equalsIgnoreCase("XML")) {
				response.then().assertThat().contentType(ContentType.XML);
			}
		}
	}

}