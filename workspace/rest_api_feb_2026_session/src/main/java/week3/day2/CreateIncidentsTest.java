package week3.day2;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import week3.day2.pojos.CreateIncident;

public class CreateIncidentsTest {
	
	@DataProvider
	public String[][] createRecords() {
		return new String[][] {
			{"RESTAPIFEB20261", "Create new record1", "hardware"},
			{"RESTAPIFEB20262", "Create new record2", "software"},
			{"RESTAPIFEB20263", "Create new record3", "inquiry"}
		};
	}
	
	@Test(dataProvider = "createRecords")
	public void createRecordsTest(String shortDescription, String description, String category) {
		CreateIncident createIncidentPojo = new CreateIncident();
		createIncidentPojo.setShortDescription(shortDescription);
		createIncidentPojo.setDescription(description);
		createIncidentPojo.setCategory(category);
				
		given()
		 .baseUri("https://dev324941.service-now.com")
		 .basePath("/api/now/table")
		 .auth()
		 .basic("admin", "e5!pRsPN%lH5")
		 .contentType(ContentType.JSON)
		 .pathParam("tableName", "incident")
		 .log().all()
		.when()
		 .body(createIncidentPojo)
		 .post("/{tableName}")
		.then()
		 .assertThat()
		 .statusCode(201)
		 .statusLine(Matchers.containsString("Created"))
		 .contentType(ContentType.JSON)
		 .body("result.short_description", Matchers.equalTo(createIncidentPojo.getShortDescription()))
		 .body("result.description", Matchers.equalTo(createIncidentPojo.getDescription()))
		 .body("result.category", Matchers.equalTo(createIncidentPojo.getCategory()));
	}

}