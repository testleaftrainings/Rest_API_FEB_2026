package week5.day1;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;

public class RestAssuredApiClient implements IApiClient {
	
	private RequestSpecification given(RequestSpecification requestSpecification) {
		return RestAssured.given()
		           .spec(requestSpecification)
		           .filters(new RequestLoggingFilter(), new ErrorResponseLoggingFilter());
	}

	@Override
	public Response get(RequestSpecification requestSpecification, String endpoint) {		
		return given(requestSpecification).get(endpoint);
	}

	@Override
	public Response post(RequestSpecification requestSpecification, String endpoint, Object requestBody) {
		if(requestBody instanceof String) {
			return given(requestSpecification).contentType(ContentType.JSON).when().body((String) requestBody).post(endpoint);
		} else if (requestBody instanceof File) {
			return given(requestSpecification).contentType(ContentType.JSON).when().body((File) requestBody).post(endpoint);
		} else if (requestBody == null) {
			return given(requestSpecification).contentType(ContentType.JSON).when().post(endpoint);
		} else {
			return given(requestSpecification).contentType(ContentType.JSON).when().body(requestBody).post(endpoint);
		}
	}

	@Override
	public Response put(RequestSpecification requestSpecification, String endpoint, Object requestBody) {
		if(requestBody instanceof String) {
			return given(requestSpecification).contentType(ContentType.JSON).when().body((String) requestBody).put(endpoint);
		} else if (requestBody instanceof File) {
			return given(requestSpecification).contentType(ContentType.JSON).when().body((File) requestBody).put(endpoint);
		} else if (requestBody == null) {
			return given(requestSpecification).contentType(ContentType.JSON).when().put(endpoint);
		} else {
			return given(requestSpecification).contentType(ContentType.JSON).when().body(requestBody).put(endpoint);
		}
	}

	@Override
	public Response patch(RequestSpecification requestSpecification, String endpoint, Object requestBody) {
		if(requestBody instanceof String) {
			return given(requestSpecification).contentType(ContentType.JSON).when().body((String) requestBody).patch(endpoint);
		} else if (requestBody instanceof File) {
			return given(requestSpecification).contentType(ContentType.JSON).when().body((File) requestBody).patch(endpoint);
		} else if (requestBody == null) {
			return given(requestSpecification).contentType(ContentType.JSON).when().patch(endpoint);
		} else {
			return given(requestSpecification).contentType(ContentType.JSON).when().body(requestBody).patch(endpoint);
		}
	}

	@Override
	public Response delete(RequestSpecification requestSpecification, String endpoint) {
		return given(requestSpecification).delete(endpoint);
	}

}