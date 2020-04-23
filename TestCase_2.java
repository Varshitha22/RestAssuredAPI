package restassuredapi;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class TestCase_2 {

	@Test
	public void tc_02() {
		
		// 1st approach but not a good one
		
		given().get("http://localhost:8080/student/list")
		.then().statusCode(200);
		
		// 2nd approach
		
		given().baseUri("http://localhost:8080/").and().basePath("/student")
		.when().get("/list")
		.then().statusCode(200).and().time(lessThan(2000L));
		
		// 3rd approach
		
		baseURI = "http://localhost:8080/";
		basePath = "/student";
		
		given()
		.when().get("/list")
		.then().statusCode(200).and().time(lessThan(2000L))
		.and().header("Content-Type", "application/json;charset=UTF-8")
		.and().body("[0].firstName", equalTo("Vernon"));
		
		JsonPath jp = given().when().get("/list").jsonPath();
		System.out.println("Name "+jp.get("[6].firstName"));
		List<String> data = jp.get("[6].courses");
		System.out.println("Courses:"+data.get(0)+"-"+data.get(1)+"-"+data.get(2));
	}
	
	@Test
	public void tc_03() {
		
		baseURI = "http://localhost:8080/";
		basePath = "/student";
		
		given()
		.when().get("/50")
		.then().statusCode(200).and().body("firstName", equalTo("Yoshio"));
		
	}
	
	@Test  //how to use query parameter
	public void tc_04() {
		
		baseURI = "http://localhost:8080/";
		basePath = "/student";
		
		given().param("programme", "Computer Science")
		.when().get("/list")
		.then().statusCode(200);
	}
}
