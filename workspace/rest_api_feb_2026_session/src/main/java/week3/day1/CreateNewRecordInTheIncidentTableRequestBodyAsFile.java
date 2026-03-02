package week3.day1;

import java.io.File;

import org.hamcrest.Matchers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateNewRecordInTheIncidentTableRequestBodyAsFile {
	
	static File request_body = new File("src/main/resources/request_body/create_incident.json");

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
