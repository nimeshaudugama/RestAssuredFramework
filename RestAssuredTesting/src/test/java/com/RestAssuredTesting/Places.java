package com.RestAssuredTesting;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.response.Response;

public class Places {
	
	private static Logger log = LogManager.getLogger(Places.class.getName());
	
	@Test
	public void searchPlace() {
		
		System.out.println("Test is running");
		
		
		 RestAssured.baseURI="https://maps.googleapis.com";
		
		
		 Response response = given().
		param("location","-33.8670522,151.1957362").
		param("radius","1500").
		param("key","AIzaSyCKzncFJfiZOzReKLK2aF7RXwWGAxB0V3U").
		
		when().
		get("/maps/api/place/nearbysearch/json").
		
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().body("results[0].name",equalTo("Sydney")).and().
		header("Server",equalTo("scaffolding on HTTPServer2"))
		
   .extract().response();
        
        // Print the response body using System.out
//        System.out.println("Response Body:");
//        System.out.println(response.getBody().asString());
        
        
        log.info(response);
		log.error("The error is for the log4j testing perpose from searchPlace");
		
	}
	
	
	



		
		

	

}
