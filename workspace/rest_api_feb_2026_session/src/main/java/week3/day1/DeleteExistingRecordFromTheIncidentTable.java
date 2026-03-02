package week3.day1;

import org.hamcrest.Matchers;

import io.restassured.RestAssured;

public class DeleteExistingRecordFromTheIncidentTable {

	public static void main(String[] args) {
		
		RestAssured.given()
		           .baseUri("https://dev324941.service-now.com")
		           .basePath("/api/now/table")
		           .pathParam("tName", "incident")
		           .pathParam("sysId", "58da099683933210ac835c65eeaad310")
		           .auth()
		           .basic("admin", "e5!pRsPN%lH5")
		           .log().all()
		           .when()
		           .delete("/{tName}/{sysId}")
		           .then()
		           .log().all()
		           .assertThat()
		           .statusCode(204)
		           .statusLine(Matchers.containsString("No Content"));
		
		
	}

}