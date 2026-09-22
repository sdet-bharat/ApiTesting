package Ecommerce;

import static io.restassured.RestAssured.given;

import java.io.File;

import Pojo.LoginRequest;
import Pojo.LoginResponse;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class EcommerceApiTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
		LoginRequest lr= new LoginRequest();
		lr.setUserEmail("bharatpoojary@gmail.com");
		lr.setUserPassword("Bharat@123");
		RequestSpecification loginRequest=given().spec(req).body(lr);
		LoginResponse loginResponse=loginRequest.when().log().all().post("api/ecom/auth/login").then().extract().response().as(LoginResponse.class);

		String id=loginResponse.getUserId();
		String token=loginResponse.getToken();
		
		
		RequestSpecification reqProductBaseReq=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("authorization", token).build();
		
		RequestSpecification reqAddProduct=given().spec(reqProductBaseReq).param("productName", "My Product").param("productAddedBy", id).param("productCategory", "fashion").param("productSubCategory", "shirts")
		.param("productPrice", "10000").param("productDescription", "Addias Originals").param("productFor", "women")
		.multiPart("productImage",new File("D:\\ScreenShot\\error.jpg"));
		
		String addProductResponse=reqAddProduct.when().post("api/ecom/product/add-product")
		.then().log().all().extract().response().asString();
		
		JsonPath js=new JsonPath(addProductResponse);
		String productId=js.getString("productId");
	}

}
