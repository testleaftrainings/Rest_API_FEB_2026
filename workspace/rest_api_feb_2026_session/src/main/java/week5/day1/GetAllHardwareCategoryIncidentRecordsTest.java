package week5.day1;

import java.util.List;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class GetAllHardwareCategoryIncidentRecordsTest {
	
	@Test
	public void get_all_hardware_category_records() {
		GetIncidentsRes response = RestAssured.given()
        .baseUri("https://dev324941.service-now.com")
        .basePath("/api/now/table")
        .pathParam("tName", "incident")
        .auth()
        .basic("admin", "/eESj0uC3k+O")
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
        .contentType(ContentType.JSON)
        .extract()
        .as(GetIncidentsRes.class);
		
		List<Result> results = response.getResult();
		
		Assert.assertEquals(results.size(), 3);
		for (Result result : results) {
			Assert.assertTrue(result.getCategory().equalsIgnoreCase("Hardware"));
		}
		
	}

}