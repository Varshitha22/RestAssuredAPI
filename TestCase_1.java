package restassuredapi;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestCase_1 {
	
	@Test
	public void tc_01()
	{
		Response response = RestAssured.get("http://localhost:8080/student/list");
		System.out.println(response.asString());//   1st way of printing the response
		System.out.println(response.getBody().asString()); // 2nd way of printing the response
		
		/* Testing the response code */
		
		int actualReponseCode = response.getStatusCode();
		System.out.println(actualReponseCode);
		Assert.assertEquals(actualReponseCode, 200);
		
		/*Testing the response time*/
		
		long actualResponseTime = response.getTime();
		System.out.println(actualResponseTime);
		Assert.assertTrue(actualResponseTime<5000L);
		
		String actualHeader = response.getHeader("Content-Type");
		System.out.println(actualHeader);
		Assert.assertEquals(actualHeader, "application/json;charset=UTF-8");
		
		String actualData = response.jsonPath().get("[5].firstName");
		System.out.println(actualData);
		Assert.assertEquals(actualData, "Harper");
	}

}
