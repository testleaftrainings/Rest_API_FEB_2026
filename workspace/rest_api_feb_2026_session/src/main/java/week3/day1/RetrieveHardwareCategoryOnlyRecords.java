package week3.day1;

import org.hamcrest.Matchers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class RetrieveHardwareCategoryOnlyRecords {

	public static void main(String[] args) {
		RestAssured.given()
		           .baseUri("https://dev324941.service-now.com")
		           .basePath("/api/now/table")
		           .pathParam("tName", "incident")
		           .auth()
		           .basic("admin", "e5!pRsPN%lH5")
		           .queryParam("category", "hardware")
		           .queryParam("sysparm_limit", "3")
		           .queryParam("sysparm_fields", "sys_id,category")
		           .log().all()
		           .when()
		           .get("/{tName}")
		           .then()
		           .log().all()
		           .assertThat()
		           .statusCode(200)
		           .statusLine(Matchers.containsString("OK"))
		           .contentType(ContentType.JSON);
	}

}