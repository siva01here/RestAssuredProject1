package com.automation.testCases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;

import com.automation.base.BaseTest;
import com.automation.utilities.RestUtils;

public class TC003_PutRequest_UpdEmpInfo extends BaseTest {
	
	String empName=RestUtils.getRandomName();
	String empSalary=Float.toString(RestUtils.getRandomSalary());
	String empAge=Integer.toString(RestUtils.getRandomAge());
	String empID="73768";
	
	@BeforeClass
	public void setUpData() {
		logger.info("*****TC003_PutRequest update employee info started******");

	}
	
	@Test
	public void verifyEmpDataExist() throws InterruptedException {
		logger.info("*****Verify employee ID exist in database before taking action****");
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		request=RestAssured.given();
		response=request.request(Method.GET, "/employee/"+empID);
		System.out.println("Emp data before put request :"+response.body().asString());	
		Thread.sleep(3000);
		logger.info("*****Update employee details based on emp ID******");
		reqParams= new JSONObject();		
		if(response.body().asString().contains(empID)) {
			reqParams.put("name", empName);
			reqParams.put("salary", empSalary);
			reqParams.put("age", empAge);
			
			request.header("content-type", "application/json");
			request.body(reqParams.toJSONString());
			response=request.request(Method.PUT, "/update/"+empID);	
			Thread.sleep(3000);
		}
		else {
			System.out.println("Something went wrong :"+response.body().asString());
		}	
	}
	
	@Test()
	public void verifyResponseBody() {
		logger.info("*****Verify updated details in the response body******" );
		System.out.println("Response body is "+response.body().asString());
		Assert.assertEquals(response.body().asString().contains(empName), true);
		Assert.assertEquals(response.body().asString().contains(empSalary), true);
		Assert.assertEquals(response.body().asString().contains(empAge), true);
	}
	
	@AfterClass 
	public void tearDown() {
		logger.info("*****TC003_PutRequest_UpdCustInfo Completed*****");
	}

}
