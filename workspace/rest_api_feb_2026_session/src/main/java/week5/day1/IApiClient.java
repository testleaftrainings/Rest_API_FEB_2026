package week5.day1;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public interface IApiClient {
	
	public Response get(RequestSpecification requestSpecification, String endpoint);
	
	public Response post(RequestSpecification requestSpecification, String endpoint, Object requestBody);
	
	public Response put(RequestSpecification requestSpecification, String endpoint, Object requestBody);
	
	public Response patch(RequestSpecification requestSpecification, String endpoint, Object requestBody);
	
	public Response delete(RequestSpecification requestSpecification, String endpoint);

}