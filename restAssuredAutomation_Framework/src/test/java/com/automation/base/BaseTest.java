package com.automation.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;


import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseTest {
	
	public static RequestSpecification request;
	public static Response response;
	public JSONObject reqParams;
	public Logger logger;
	
	@BeforeClass
	public void setUp() throws InterruptedException {
		logger=Logger.getLogger("BaseTest");
		PropertyConfigurator.configure("D:\\EclipseProjects\\restAssuredAutomation_Framework\\src\\test\\java\\log4j.properties");
		logger.setLevel(Level.DEBUG);
	}

}
