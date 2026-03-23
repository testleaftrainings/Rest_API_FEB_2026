package week6.day1;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

public class StubGetMethodWithJsonResponse {

	public static void main(String[] args) {
		
		MappingBuilder requestMapping = WireMock.get("/welcome/json");
		
		ResponseDefinitionBuilder responseMapping = WireMock.aResponse().withStatus(200)
		                    .withHeader("Content-Type", "application/json")
		                    .withBody("""
		                    		   { 
		                    		     "successMessage": "OK"
		                    		   }
		                    		  """);
		
		WireMock.stubFor(requestMapping.willReturn(responseMapping));

	}

}
