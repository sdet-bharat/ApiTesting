import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
public class CreateBug {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI="https://bharatpoojary.atlassian.net/";
		
		String Response=given().header("Content-Type","application/json").header("Authorization","Basic YmhhcmF0cG9vamFyeTExMUBnbWFpbC5jb206QVRBVFQzeEZmR0YwMWxwNU9QdFFnc3FjTENKMzNfRjd2VE9jNDQxcEJDSnJMc2t5eDdXNGcxei1GdFhnS2dINEFuM3NZUEVmUGd3bk1aZzhDMzhMaUxlM0EzbTgwQ1p5WW5iNnNkSGV1QWVHTWFmOWN3SWZLYnd0Y0tfSmtlQ0h5SExtMmlubW4zY1hBZjh3NWZibW0zcXR2bHpIaVlqWTJMQ0N2WTl0RVVsRS1aX0s2akwydU9nPTQxQkUyMkY3")
		.body("{\r\n"
				+ "  \"fields\": {\r\n"
				+ "    \"project\": {\r\n"
				+ "      \"key\": \"SCRUM\"\r\n"
				+ "    },\r\n"
				+ "    \"summary\": \"SubTitle are not working- through automation\",\r\n"
				+ "    \"description\": {\r\n"
				+ "      \"type\": \"doc\",\r\n"
				+ "      \"version\": 1,\r\n"
				+ "      \"content\": [\r\n"
				+ "        {\r\n"
				+ "          \"type\": \"paragraph\",\r\n"
				+ "          \"content\": [\r\n"
				+ "            {\r\n"
				+ "              \"type\": \"text\",\r\n"
				+ "              \"text\": \"Creating of an issue using project keys and issue type names using the REST API\"\r\n"
				+ "            }\r\n"
				+ "          ]\r\n"
				+ "        }\r\n"
				+ "      ]\r\n"
				+ "    },\r\n"
				+ "    \"issuetype\": {\r\n"
				+ "      \"name\": \"Bug\"\r\n"
				+ "    }\r\n"
				+ "  }\r\n"
				+ "}")
		.when().post("rest/api/3/issue")
		.then().log().all().assertThat().statusCode(201).extract().response().asString();
		
		//System.out.println(Response);
		
		JsonPath js=new JsonPath(Response);
		String id=js.getString("id");
		System.out.println("Here is the id:"+id);
		
		given().header("X-Atlassian-Token","no-check").header("Authorization","Basic YmhhcmF0cG9vamFyeTExMUBnbWFpbC5jb206QVRBVFQzeEZmR0YwMWxwNU9QdFFnc3FjTENKMzNfRjd2VE9jNDQxcEJDSnJMc2t5eDdXNGcxei1GdFhnS2dINEFuM3NZUEVmUGd3bk1aZzhDMzhMaUxlM0EzbTgwQ1p5WW5iNnNkSGV1QWVHTWFmOWN3SWZLYnd0Y0tfSmtlQ0h5SExtMmlubW4zY1hBZjh3NWZibW0zcXR2bHpIaVlqWTJMQ0N2WTl0RVVsRS1aX0s2akwydU9nPTQxQkUyMkY3")
		.pathParam("key", id)
		.multiPart("file", new File("C:\\Users\\Bharat\\OneDrive\\Pictures\\Screenshots 1\\Screenshot 2026-05-21 171846.png"))
		.when().post("rest/api/3/issue/{key}/attachments")
		.then().log().body().assertThat().statusCode(200).body("filename[0]",equalTo("Screenshot 2026-05-21 171846.png"));
	}

}
