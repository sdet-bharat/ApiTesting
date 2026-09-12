package maps;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {
	public static void main(String[] args) {
		AddPlace ap=new AddPlace();
		ap.setAccuracy(50);
		ap.setName("Frontline house");
		ap.setPhone_number( "(+91) 983 893 3937");
		ap.setAddress("29, side layout, cohen 09");
		ap.setWebsite("http://google.com");
		ap.setLanguage("French-IN");
		ArrayList<String> myList=new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		ap.setTypes(myList);
		Location l=new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		ap.setLocation(l);
		
		RequestSpecification requestSpec=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		ResponseSpecification responseSpec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		RequestSpecification res=given().spec(requestSpec).body(ap);
		
		String response=res.when().post("/maps/api/place/add/json")
		.then().spec(responseSpec).extract().response().asString();
		
		System.out.println(response);
		
	}

}
