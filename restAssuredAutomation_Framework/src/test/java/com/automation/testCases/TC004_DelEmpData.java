package com.automation.testCases;
import com.automation.base.BaseTest;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TC004_DelEmpData extends BaseTest{
	RequestSpecification request;
	Response response;
	String empDelID="73687";
	
	@BeforeClass
	public void setUpData() {
		logger.info("*****TC_004 delete employee data test started*****");
	}
	
	@Test
	public void verifyEmpDataExist() throws InterruptedException {
		logger.info("*****Verify employee ID exist in database before taking action****");
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		request=RestAssured.given();
		response=request.request(Method.GET, "/employee/"+empDelID);
		System.out.println("Emp data before delete request :"+response.body().asString());
		Thread.sleep(5000);
		logger.info("****Delete employee data****");
		if(response.body().asString().contains(empDelID)) {
			System.out.println(empDelID);
			response=request.request(Method.DELETE,"/delete/"+empDelID);
			Thread.sleep(3000);		
		}
		else {
			System.out.println("Employee data doesnt exist "+response.body().asString());
		}
	}
	
	@Test
	public void getRespStatusCode() {
		logger.info("*****Verify status code of delete request*****");
		System.out.println("Response status code is "+response.getStatusCode());
		Assert.assertEquals(response.getStatusCode()==200, true);
	}
	
	@AfterClass
	public void tearDown() {
		logger.info("*****TC004_Del Emp data is completed*****");
	}

}
