package week6.day2;

import org.hamcrest.Matchers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import week6.day2.pojos.RetrieveAllRecordsSampleResponse;

public class ValidateJSONSchemaFromFileForRetrieveAllRecordsApiUsingSchemaGenerator {
	
	public static void main(String[] args) {
		RestAssured.given()
        .auth()
        .basic("admin", "/eESj0uC3k+O")
        .queryParam("sysparm_limit", "1")
        .log().all()
        .when()
        .get("https://dev324941.service-now.com/api/now/table/incident")
        .then()
        .log().all()
        .assertThat()
        .statusCode(200)
        .statusLine(Matchers.containsString("OK"))
        .contentType(ContentType.JSON)
        .body(JsonSchemaValidator.matchesJsonSchema(GenerateJsonSchemaUsingPojo.create(RetrieveAllRecordsSampleResponse.class)));
	}

}