package Ecommerce;

import static io.restassured.RestAssured.*;

import Pojo.LoginRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class EcommerceApiTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/api/ecom/auth/login").setAccept(ContentType.JSON).build();
		LoginRequest lr= new LoginRequest();
		lr.setUserName("bharatpoojary@gmail.com");
		lr.setPassword("Bharat@123");
		RequestSpecification loginRequest=given().spec(req).body(lr);
		
		loginRequest.when().post("api/ecom/auth/login").then().extract().response().as(null)
		
		
	}

}
