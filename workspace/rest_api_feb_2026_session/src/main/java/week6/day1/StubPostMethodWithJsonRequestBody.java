package week6.day1;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

public class StubPostMethodWithJsonRequestBody {

	public static void main(String[] args) {		
		
		WireMockServer mockServer = new WireMockServer();
		mockServer.start();
		
		MappingBuilder requestMapping = WireMock.post("/create/record")
		        .withHeader("Content-Type", WireMock.equalTo("application/json"))
		        .withRequestBody(WireMock.equalToJson("""
		        		{
		        		  "short_description": "RESATAPIFEB2025"	
		        		}
		        		"""));
		ResponseDefinitionBuilder requesMapping = WireMock.aResponse().withStatus(201)
		                    .withHeader("Content-Type", "application/json")
		                    .withBodyFile("mocking-response.json");
		
		mockServer.stubFor(requestMapping.willReturn(requesMapping));  
		
		System.out.println("MockServer is created!");
	  

	}

}
