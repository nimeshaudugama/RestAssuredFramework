package com.RestAssuredTesting;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.RestAssured;

public class GetPostComments {
	
	Properties pro = new Properties();
	
	@BeforeTest
	public void getData() throws IOException {
		

		FileInputStream fis = new FileInputStream("/Users/nimesha/eclipse-workspace/RestAssuredTesting/src/main/java/properties/environment.properties");
		pro.load(fis);
		pro.get("HOST");
		
		}
	
	private static Logger log = LogManager.getLogger(GetPostComments.class.getName());
	
	@Test
	public void getComments() {
		
		RestAssured.baseURI=pro.getProperty("HOST");
		
		Response response = given().
		param("email","Eliseo@gardner.biz").
		when().
		get("/comments").
		
		then().assertThat().statusCode(200).and().body("[0].email", equalTo("Eliseo@gardner.biz")).and().
		
		extract().response();
		
		//System.out.print(response.getBody().asString());
		
		log.info(response);
		log.error("The error is for the log4j testing perpose from getComments");
		
	}

}
