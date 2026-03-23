package week6.day1;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

public class MyFirstWiremockScript {

	public static void main(String[] args) {
		
		// Request Mocking
		MappingBuilder requestMocking = WireMock.get("/welcome-message");
		
		// Response Mocking
		ResponseDefinitionBuilder responseMocking = WireMock.aResponse().withStatus(200)
				                                     .withBody("Hi! Welcome to WireMock world.");
		// Mapping request and response
		WireMock.stubFor(requestMocking.willReturn(responseMocking));
		
	}

}