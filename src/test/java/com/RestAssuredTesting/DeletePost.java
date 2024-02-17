package com.RestAssuredTesting;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import utilities.Resources;

public class DeletePost {
	
	Properties pro = new Properties();
	
	@BeforeTest
	
	public void getData() throws IOException {
	

	FileInputStream fis = new FileInputStream((System.getProperty("user.dir")+"/src/main/java/properties/environment.properties"));
	pro.load(fis);
	pro.get("HOST");
	
	}
	
	private static Logger log = LogManager.getLogger(DeletePost.class.getName());
	
	@Test
	public void deletePost() {
		
		RestAssured.baseURI= pro.getProperty("HOST");
		
		
		Response response=given().
		when().
		delete(Resources.getDeletePosts()).
		then().assertThat().statusCode(200).
		extract().response();
		
		log.info(response);
		log.debug("The test was a success");
		
		//System.out.print(response.getBody().asString());
	}

}
