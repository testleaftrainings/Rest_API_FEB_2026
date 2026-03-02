package week3.day1;

import org.hamcrest.Matchers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateNewRecordInTheIncidentTableRequestBodyAsString {
	
	static String request_body = """
			{
              "short_description": "RESTAPIFEB2026",
              "description": "Update the record based on the PUT method",
              "category": "hardware"
            } 
			""";

	public static void main(String[] args) {
		RestAssured.given()
        .baseUri("https://dev324941.service-now.com")
        .basePath("/api/now/table")
        .pathParam("tName", "incident")
        .auth()
        .basic("admin", "e5!pRsPN%lH5")
        .header("Content-Type", "application/json")
        .log().all()
        .when()
        .body(request_body)
        .post("/{tName}")
        .then()
        .log().all()
        .assertThat()
        .statusCode(201)
        .statusLine(Matchers.containsString("Created"))
        .contentType(ContentType.JSON);		
	}

}
