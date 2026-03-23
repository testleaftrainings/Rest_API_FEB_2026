package week6.day1;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

public class StubPostMethodWithJsonRequestBodyUsingJsonPath {

	public static void main(String[] args) {
		
		MappingBuilder requestMapping = WireMock.post("/create/record")
		        .withHeader("Content-Type", WireMock.equalTo("application/json"))
		        .withRequestBody(WireMock.matchingJsonPath("$.short_description"));
		ResponseDefinitionBuilder requesMapping = WireMock.aResponse().withStatus(201)
		                    .withHeader("Content-Type", "application/json")
		                    .withBody("""
		                    		  {
		                    		    "sys_id": "78esydshseyseysg25478esyesy"
		                    		  }
		                    		  """);
		
	   WireMock.stubFor(requestMapping.willReturn(requesMapping));		

	}

}
