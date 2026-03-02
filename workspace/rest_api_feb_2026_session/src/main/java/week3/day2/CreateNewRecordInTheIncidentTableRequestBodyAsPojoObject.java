package week3.day2;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import week3.day2.pojos.CreateIncident;

public class CreateNewRecordInTheIncidentTableRequestBodyAsPojoObject {

	public static void main(String[] args) {
		
		CreateIncident createIncidentPojo = new CreateIncident();
		createIncidentPojo.setShortDescription("RESTAPIFEB2026");
		createIncidentPojo.setDescription("Create new incident record usig POST, request body as POJO Class");
				
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
		 .statusCode(201);
		 
	}

}