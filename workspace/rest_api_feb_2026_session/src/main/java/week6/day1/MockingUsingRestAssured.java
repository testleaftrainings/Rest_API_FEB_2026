package week6.day1;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class MockingUsingRestAssured {
	
	WireMockServer mockServer;
	
	@BeforeClass
	public void beforeClass() {
		mockServer = new WireMockServer();
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
		
	}

	@Test
	public void testTheStub() {
		RestAssured.given()
		           .baseUri("http://localhost:8080")
		           .contentType(ContentType.JSON)
		           .log().all()
		           .when()
		           .body("""
		           		{
		        		  "short_description": "RESATAPIFEB2025"	
		        		}""")
		           .post("/create/record")
		           .then()
		           .log().all()
		           .assertThat()
		           .statusCode(201);
	}
	
	@AfterClass
	public void afterClass() {
		mockServer.stop();
	}

}