package week4.day1;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import week3.day2.pojos.UpdateIncident;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ServinowIncidentTableE2eTest {
	
	private static String sys_id;
	private UpdateIncident updateIncident = new UpdateIncident();
	
	@Test(priority = 1)
	public void create_new_record() {
		sys_id = given()
		  .baseUri("https://dev354951.service-now.com")
		  .basePath("/api/now/table")
		  .auth()
		  .basic("admin", "noJ73hE!aAG!")
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
		  .body("result", hasKey("sys_id"))
		  .body("result.sys_id", not(emptyOrNullString()))
		  .body("result.short_description", emptyString())
		  .body("result.description", emptyString())
		  .body("result.category", equalTo("inquiry"))
		  .body("result.state", equalTo("1"))
		  .body("result.category", equalTo("inquiry"))
		  .body("result.active", equalTo("true"))
		  .extract()
		  .jsonPath()
		  .getString("result.sys_id");		
	}
	
	@Test(priority = 2)
	public void get_a_record() {
		given()
		  .baseUri("https://dev354951.service-now.com")
		  .basePath("/api/now/table")
		  .auth()
		  .basic("admin", "noJ73hE!aAG!")
		  .pathParam("tableName", "incident")	
		  .pathParam("sysId", sys_id)
		  .log().all()
		.when()
		  .get("/{tableName}/{sysId}")
		.then()
		  .log().all()
		  .assertThat()
		  .statusCode(200)
		  .statusLine(containsString("OK"))
		  .contentType(ContentType.JSON)
		  .body("result", hasKey("sys_id"))
		  .body("result.sys_id", equalTo(sys_id));
	}

	@Test(priority = 3)
	public void update_existing_record() {
		updateIncident.setShortDescription("RESTAPIFEB2026");
		updateIncident.setDescription("Update existing record using put method");
		updateIncident.setCategory("hardware");
		given()
		  .baseUri("https://dev354951.service-now.com")
		  .basePath("/api/now/table")
		  .auth()
		  .basic("admin", "noJ73hE!aAG!")
		  .pathParam("tableName", "incident")	
		  .pathParam("sysId", sys_id)
		  .contentType(ContentType.JSON)
		  .log().all()
		.when()
		  .body(updateIncident)
		  .put("/{tableName}/{sysId}")
		.then()
		  .log().all()
		  .assertThat()
		  .statusCode(200)
		  .statusLine(containsString("OK"))
		  .contentType(ContentType.JSON)
		  .body("result.short_description", equalTo(updateIncident.getShortDescription()))
		  .body("result.description", equalTo(updateIncident.getDescription()))
		  .body("result.category", equalTo(updateIncident.getCategory()));
	}
	
	@Test(priority = 4)
	public void delete_existing_record() {
		given()
		  .baseUri("https://dev354951.service-now.com")
		  .basePath("/api/now/table")
		  .auth()
		  .basic("admin", "noJ73hE!aAG!")
		  .pathParam("tableName", "incident")	
		  .pathParam("sysId", sys_id)
		  .log().all()
		.when()
		  .delete("/{tableName}/{sysId}")
		.then()
		  .log().all()
		  .assertThat()
		  .statusCode(204)
		  .statusLine(containsString("No Content"))
		  .time(lessThan(5000L));
	}
	
	@Test(priority = 5)
	public void is_record_deleted_successfully() {
		given()
		  .baseUri("https://dev354951.service-now.com")
		  .basePath("/api/now/table")
		  .auth()
		  .basic("admin", "noJ73hE!aAG!")
		  .pathParam("tableName", "incident")	
		  .pathParam("sysId", sys_id)
		  .log().all()
		.when()
		  .get("/{tableName}/{sysId}")
		.then()
		  .log().all()
		  .assertThat()
		  .statusCode(404)
		  .statusLine(containsString("Not Found"))
		  .contentType(ContentType.JSON);
	}
}