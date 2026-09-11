import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.List;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
public class OAuth {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI="https://rahulshettyacademy.com/oauthapi/";
		String Response=given().formParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.formParams("grant_type","client_credentials").formParams("scope","trust")
		.when().post("oauth2/resourceOwner/token")
		.then().extract().response().asString();
		
		System.out.println(Response);
		
		JsonPath js=new JsonPath(Response);
		String token=js.getString("access_token");
		System.out.println(token);
		
		GetCourse gc=given().queryParam("access_token", token)
		.when().get("getCourseDetails").as(GetCourse.class);
		
		System.out.println(gc.getLinkedIn());
		
		List<Api> api=gc.getCourses().getApi();
		
		for(int i=0;i<api.size();i++) {
			
			if(api.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
				System.out.println(api.get(i).getPrice());
			}
		}
		
		List<WebAutomation> webAuto=gc.getCourses().getWebAutomation();
		
		for(int i=0;i<webAuto.size();i++) {
			System.out.println(webAuto.get(i).getCourseTitle());
		}
	}

}
