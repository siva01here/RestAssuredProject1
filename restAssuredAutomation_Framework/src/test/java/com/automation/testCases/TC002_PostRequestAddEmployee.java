package com.automation.testCases;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.automation.base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import com.automation.utilities.RestUtils;

public class TC002_PostRequestAddEmployee extends BaseTest {
	
	String empName=RestUtils.getRandomName();
	String empSal=Float.toString(RestUtils.getRandomSalary());
	String empAge=Integer.toString(RestUtils.getRandomAge());
		
	@BeforeClass
	public void setUpCreateEmployee() throws InterruptedException {
		logger.info("*****TC002 Started for Post request*****");
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		request=RestAssured.given();
		reqParams= new JSONObject();
		reqParams.put("name", empName);
		reqParams.put("salary",empSal);
		reqParams.put("age", empAge);
		
		request.header("content-type", "application/json");
		request.body(reqParams.toJSONString());
		
		response=request.request(Method.POST, "/create");
		Thread.sleep(3000);
	}
	
	@Test
	public void verifyStatusCode() {
		logger.info("*****Verifying status code of the response*****");
		System.out.println("Response Status code is "+response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test
	public void verifyResponseBody() {
		logger.info("*****Verify response body contains the input params*****");
		System.out.println("Response body is "+response.body().asString());
		Assert.assertEquals(response.body().asString().contains(empName),true);
		Assert.assertEquals(response.body().asString().contains(empSal),true);
		Assert.assertEquals(response.body().asString().contains(empAge), true);
	}
	
	@Test
	public void verifyStatusLine() {
		logger.info("*****Verify status line of the response*****");
		System.out.println(response.getStatusLine());
	}
	
	@AfterClass
	public void tearDown() {
		logger.info("*****TC002_PostRequestAddEmployee Test completed******");
	}
	
}
