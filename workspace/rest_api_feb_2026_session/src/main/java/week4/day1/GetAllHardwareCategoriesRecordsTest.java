package week4.day1;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import week3.day2.pojos.CreateIncident;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class GetAllHardwareCategoriesRecordsTest {
	
	private Map<String, String> queryParams = new HashMap<String, String>();
	
	@BeforeClass
	public void beforeClass() {
		queryParams.put("category", "hardware");
		queryParams.put("sysparm_limit", "3");
		queryParams.put("sysparm_fields", "sys_id,category");
	}
	
	@Test
	public void validateTheHardwareCategoryResponseBody() {
		given()
		  .baseUri("https://<your-instance-id>.service-now.com")
		  .basePath("/api/now/table")
		  .auth()
		  .basic("admin", "<your-password>")
		  .pathParam("tableName", "incident")
		  .queryParams(queryParams)
		  .log().all()
		.when()
		  .get("/{tableName}")		  		  
		.then()
		  .log().ifValidationFails(LogDetail.ALL)
		  .assertThat()
		  .statusCode(200)
		  .statusLine(containsString("OK"))
		  .contentType(ContentType.JSON)
		  .body("result", hasSize(3))
		  .body("result", everyItem(hasKey("sys_id")))
		  .body("result", everyItem(hasKey("category")))
		  .body("result.category", everyItem(equalToIgnoringCase("hardware")));
	}

}