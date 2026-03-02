package week3.day1;

import java.io.File;

import org.hamcrest.Matchers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class UpdateExistingRecordInTheIncidentTableRequestBodyAsFile {
	
	static File request_body = new File("src/main/resources/request_body/update_incident.json");

	public static void main(String[] args) {
		RestAssured.given()
        .baseUri("https://dev324941.service-now.com")
        .basePath("/api/now/table")
        .pathParam("tName", "incident")
        .pathParam("sysId", "57c0ddd683933210ac835c65eeaad343")
        .auth()
        .basic("admin", "e5!pRsPN%lH5")
        .header("Content-Type", "application/json")
        .log().all()
        .when()
        .body(request_body)
        .patch("/{tName}/{sysId}")
        .then()
        .log().all()
        .assertThat()
        .statusCode(200)
        .statusLine(Matchers.containsString("OK"))
        .contentType(ContentType.JSON);
	}

}
