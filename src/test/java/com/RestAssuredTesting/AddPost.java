package com.RestAssuredTesting;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import utilities.Payload;
import utilities.Resources;


public class AddPost {
	
	Properties pro = new Properties();
	
	@BeforeTest

	
	public void getData() throws IOException {
		FileInputStream fis = new FileInputStream((System.getProperty("user.dir")+"/src/main/java/properties/environment.properties"));
		pro.load(fis);
		pro.get("HOST");
		
	}
	
	
	
	private static Logger log = LogManager.getLogger(AddPost.class.getName());
	
	int userId;
	int postId;
	
	@Test
	public void addPost() {
		
	
		
		log.info("Add post information");
		RestAssured.baseURI=pro.getProperty("HOST");
		
		Response response=given().
			// contentType(ContentType.JSON).	
		   body(Payload.getAddPostData()).
		   header("Content-Type", "application/json").
		   when().
		   post(Resources.getAddPost()).
		   then().assertThat().statusCode(201).and().body("userId",equalTo(1)).
		   
		   extract().response();
		
		//System.out.print(response.getBody().asString());
		
		String res = response.asString();
		log.info(res);
		JsonPath js = new JsonPath(res);
		userId = js.get("userId");
		
	}
	
	@Test
	
		public void getAddedPost() {
		
RestAssured.baseURI=pro.getProperty("HOST");;
		
		Response response = given().
		
		when().
		get(Resources.getGetPost()).
		
		then().assertThat().statusCode(200).and().body("[0].userId", equalTo(userId)).and().
		
		extract().response();
		
		//System.out.print(response.getBody().asString());
		
		String res=response.asString();
		JsonPath js = new JsonPath(res);
		postId=js.get("[0].id");
		
		
		
	}
	
	//Search all posts,extract one email 
	
	@Test
	
		public void getAddedPostCommentor() {
		
RestAssured.baseURI=pro.getProperty("HOST");;
		
		Response response = given().
				param("postId",postId).
		
		when().
		get(Resources.getGetComments()).
		
		then().assertThat().statusCode(200).and().body("[0].email", equalTo("Eliseo@gardner.biz")).and().body("[0].postId",equalTo(postId)).
		
		extract().response();
		
		//System.out.print(response.getBody().asString());
		
	
		
	}
	
	

	
	

}
