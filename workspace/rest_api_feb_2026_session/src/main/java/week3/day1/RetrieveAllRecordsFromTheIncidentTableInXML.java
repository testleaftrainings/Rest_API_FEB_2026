package week3.day1;

import org.hamcrest.Matchers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class RetrieveAllRecordsFromTheIncidentTableInXML {

	public static void main(String[] args) {
		RestAssured.given()
		           .header("Accept", "application/xml")
		           .auth()
		           .basic("admin", "e5!pRsPN%lH5")
		           .log().all()
		           .when()
		           .get("https://dev324941.service-now.com/api/now/table/incident")
		           .then()
		           .log().all()
		           .assertThat()
		           .statusCode(200)
		           .statusLine(Matchers.containsString("OK"))
		           .contentType(ContentType.XML);		
	}

}