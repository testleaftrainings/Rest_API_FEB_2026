package week6.day2;

import java.io.File;

import org.hamcrest.Matchers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class ValidateJSONSchemaFromFileForRetrieveAllRecordsApi {
	
	static File jsonSchema = new File("src/test/resources/retrieve-all-records-schema.json");

	public static void main(String[] args) {
		RestAssured.given()
        .auth()
        .basic("admin", "/eESj0uC3k+O")
        .log().all()
        .when()
        .get("https://dev324941.service-now.com/api/now/table/incident")
        .then()
        .log().all()
        .assertThat()
        .statusCode(200)
        .statusLine(Matchers.containsString("OK"))
        .contentType(ContentType.JSON)
        .body(JsonSchemaValidator.matchesJsonSchema(jsonSchema));
	}

}