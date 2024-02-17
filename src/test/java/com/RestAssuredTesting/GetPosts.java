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

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utilities.Resources;

public class GetPosts {
	
	
Properties pro = new Properties();
	
@BeforeTest	
public void getData() throws IOException {
	

	FileInputStream fis = new FileInputStream((System.getProperty("user.dir")+"/src/main/java/properties/environment.properties"));
	pro.load(fis);
	pro.get("HOST");
	
	}
	
	private static Logger log = LogManager.getLogger(GetPosts.class.getName());
	
	@Test
	
	public void getPost()
	{
		
		RestAssured.baseURI =pro.getProperty("HOST");
		
		Response response=
		given().
		param("id","1").
		when().
		get(Resources.getGetPost()).
		then().assertThat().statusCode(200).and().body("[0].id",equalTo(1))
		
		.extract().response();
		
		//System.out.print(response.getBody().asString());
		
		log.info(response);
		log.error("The error is for the log4j testing perpose from getPost");
		
		//.and().
		//body("title",equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"))
		
		
		
	}
	
}
