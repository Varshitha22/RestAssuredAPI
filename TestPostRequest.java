package restassuredapi;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;

public class TestPostRequest {
	
	@Test
	public void post_test_01() {
		baseURI = "http://localhost:8080/";
		basePath = "/student";
		
		given().contentType(ContentType.JSON).body("{\r\n" + 
				"        \"firstName\": \"Varshitha\",\r\n" + 
				"        \"lastName\": \"Reddy\",\r\n" + 
				"        \"email\": \"Varshithanandu@gmail.com\",\r\n" + 
				"        \"programme\": \"Telecom\",\r\n" + 
				"        \"courses\": [\r\n" + 
				"            \"Signals and System\",\r\n" + 
				"            \"Other Telecom Subjects\"\r\n" + 
				"        ]\r\n" + 
				"    }")
		.when().post("/")
		.then().statusCode(201).and().body("msg", equalTo("Student added"));
	}

}
