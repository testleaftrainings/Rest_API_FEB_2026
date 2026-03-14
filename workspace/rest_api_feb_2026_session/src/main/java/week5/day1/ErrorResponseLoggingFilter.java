package week5.day1;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class ErrorResponseLoggingFilter implements Filter {

	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext context) {
		Response response = context.next(requestSpec, responseSpec);
		if(response.getStatusCode() >= 400) {
			System.err.println("Status Code: "+response.getStatusCode());
			System.err.println("Status Message: "+response.getStatusLine().split(" ", 3)[2]);			
			System.err.println("Response Body: "+response.getBody().asPrettyString());
		}
		return response;
	}

}