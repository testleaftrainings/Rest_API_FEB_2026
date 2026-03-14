package week5.day1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class CreateIncidentTest {
	
	@Test
	public void create_new_record() {
		CreateIncidentRes response = given()
		  .baseUri("https://dev324941.service-now.com")
		  .basePath("/api/now/table")
		  .auth()
		  .basic("admin", "/eESj0uC3k+O")
		  .pathParam("tableName", "incident")
		  .contentType(ContentType.JSON)
		  .log().all()
		.when()
		  .post("/{tableName}")
		.then()
		  .log().all()
		  .assertThat()
		  .statusCode(201)
		  .statusLine(containsString("Created"))
		  .contentType(ContentType.JSON)
		  .extract()
		  .as(CreateIncidentRes.class);
		System.out.println(response.getResult().getSysId());
	}

}