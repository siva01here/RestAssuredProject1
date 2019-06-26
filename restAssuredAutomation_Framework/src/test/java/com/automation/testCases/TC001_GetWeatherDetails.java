package com.automation.testCases;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.automation.base.BaseTest;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_GetWeatherDetails extends BaseTest {
	
	@BeforeClass
	public void setUpConnectivity() throws InterruptedException {
		logger.info("*****Test started TC001_GetWeatherDetails*****");
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city";
		request=RestAssured.given();
		response=request.request(Method.GET, "/Kerala");
		Thread.sleep(3000);
	}
	
	@Test
	public void validateStatusCode() {
		logger.info("*****Vaidating status code of the response*****");
		if(response.getStatusCode()==200) {
			System.out.println("Status code is valid "+response.getStatusCode());
		}
		else {
		System.out.println("Status code is invalid "+response.getStatusCode());
		}
	}
	
	@Test
	public void validateStatusLine() {
		logger.info("*****Validating status line of the response*****");
		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
		System.out.println("Status line of the response is "+response.getStatusLine());
	}
	
	@Test
	public void validateResponseBody() {
		logger.info("*****Validating response body*****");
		Assert.assertTrue(response.body().asString().contains("Kerala"), "Response body contains the valid city name");
		System.out.println("Response body is "+response.body().asString());
	}
	
	@Test
	public void validateContentTypeHeader() {
		logger.info("*****Validating content-type Header field*****");
		System.out.println("Content type header value is "+response.getHeader("content-type"));
		Assert.assertTrue(response.getHeader("content-type").equalsIgnoreCase("application/json"), "Content type header is valid...");
		
	}
	
	@Test
	public void validateResponseTime() {
		logger.info("*****Validating response time*****");
		System.out.println("Response time is "+response.getTime());
	}
	
	@AfterClass
	public void tearDown() {
		logger.info("*****Test Completed*****");
	}

}
