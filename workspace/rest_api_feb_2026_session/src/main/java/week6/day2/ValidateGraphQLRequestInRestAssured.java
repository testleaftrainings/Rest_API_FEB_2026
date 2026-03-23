package week6.day2;

import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ValidateGraphQLRequestInRestAssured {
	
	static String graphQL = """
			query {
    viewer {
    login
    name
    avatarUrl
    company
    repositories {            
      totalCount
      totalDiskUsage
    }
    followers {
      totalCount      
    }
  }
}
			""";

	public static void main(String[] args) {
		RestAssured.given()
		           .baseUri("https://api.github.com")
		           .header("Authorization", "Bearer <token>")
		           .contentType(ContentType.JSON)
		           .log().all()
		           .when()
		           .body(convertGraphQLQueryToJsonString(graphQL))
		           .post("/graphql")
		           .then()
		           .log().all()
		           .assertThat()
		           .statusCode(200);		        
	}
	
	static String convertGraphQLQueryToJsonString(String grqphQL) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("query", grqphQL);
		return jsonObject.toString();
	}

}