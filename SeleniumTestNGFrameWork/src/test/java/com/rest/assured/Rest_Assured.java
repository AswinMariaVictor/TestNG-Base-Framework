package com.rest.assured;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Rest_Assured {

	public void getMethod() {
		RestAssured.baseURI = "http://localhost:3000";
		RequestSpecification requestSpecification = RestAssured.given();
		Response response = requestSpecification.request(Method.GET, "/employees");
		System.out.println(response.asPrettyString());
		System.out.println(response.getStatusLine());
	}
	
	public void postMethod() {
		RestAssured.baseURI = "http://localhost:3000";
		RequestSpecification requestSpecification = RestAssured.given()
																.header("Content-Type", "application/json")
																.body("{ sample json}");
		Response response = requestSpecification.request(Method.POST, "/employees");
		System.out.println(response.asPrettyString());
		System.out.println(response.getStatusLine());
	}
}