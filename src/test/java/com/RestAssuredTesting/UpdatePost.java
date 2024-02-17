package com.RestAssuredTesting;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utilities.Payload;
import utilities.Resources;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UpdatePost {
	
Properties pro = new Properties();
	
@BeforeTest	
public void getData() throws IOException {
	

	FileInputStream fis = new FileInputStream((System.getProperty("user.dir")+"/src/main/java/properties/environment.properties"));
	pro.load(fis);
	pro.get("HOST");
	
	}
	
	private static Logger log = LogManager.getLogger(UpdatePost.class.getName());
	
	@Test
	public void updatePost()
	{
		RestAssured.baseURI=pro.getProperty("HOST");
		
		Response response = given().
		 body(Payload.getUpdatePostData()).
		 header("Content-Type","application/json").
		 when().
		// put("/posts/1").
		 put(Resources.getDeletePosts()).
		 then().assertThat().statusCode(200).and().body("title",equalTo("First Post changed through rest")).
		 extract().response();
		
		//System.out.print(response.getBody().asString());
		
		
		 log.info(response);
			log.error("The error is for the log4j testing perpose from updatePost");
		
		
		
	}
}
