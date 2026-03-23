package week6.day1;

import org.hamcrest.Matchers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateOAuthToken {

	public static void main(String[] args) {
		String accessToken = RestAssured.given()
		           .baseUri("https://dev324941.service-now.com")
		           .contentType(ContentType.URLENC)
		           .log().all()
		           .when()
		           .formParam("grant_type", "password")
		           .formParam("client_id", "6ba9b7ef5fc94ab6861af11764e8b919")
		           .formParam("client_secret", "QC.3oHnr7mROTuoRe,`:7[s$[$}Gs4u9")
		           .formParam("username", "admin")
		           .formParam("password", "/eESj0uC3k+O")
		           .post("/oauth_token.do")
		           .then()
		           .log().all()
		           .statusCode(200)
		           .contentType(ContentType.JSON)
		           .extract()
		           .jsonPath()
		           .getString("access_token");
		System.out.println(accessToken);
		RestAssured.given()
        .baseUri("https://dev324941.service-now.com")
        .basePath("/api/now/table")
        .pathParam("tName", "incident")
        //.header("Authorization", "Bearer "+accessToken)
        .auth()
        .oauth2(accessToken)
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
