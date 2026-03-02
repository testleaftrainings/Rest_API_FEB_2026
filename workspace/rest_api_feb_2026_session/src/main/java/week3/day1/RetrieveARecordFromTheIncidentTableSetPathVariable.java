package week3.day1;

import org.hamcrest.Matchers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class RetrieveARecordFromTheIncidentTableSetPathVariable {

	public static void main(String[] args) {
		
		RestAssured.given()
		           .baseUri("https://dev324941.service-now.com")
		           .basePath("/api/now/table")
		           .pathParam("tName", "incident")
		           .pathParam("sysId", "f9df2f1783203210ac835c65eeaad308")
		           .auth()
		           .basic("admin", "e5!pRsPN%lH5")
		           .log().all()
		           .when()
		           .get("/{tName}/{sysId}")
		           .then()
		           .log().all()
		           .assertThat()
		           .statusCode(200)
		           .statusLine(Matchers.containsString("OK"))
		           .contentType(ContentType.JSON);
		
		
	}

}