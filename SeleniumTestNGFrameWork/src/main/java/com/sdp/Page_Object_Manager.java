package com.sdp;

import org.openqa.selenium.WebDriver;

import com.pom.HomePage;

public class Page_Object_Manager {

	public WebDriver driver;
	private HomePage homePageObject;
	
	public Page_Object_Manager(WebDriver _driver)
	{
		this.driver = _driver;
	}

	public HomePage getHomePageObject()
	{
		return homePageObject = new HomePage(driver);
	}
}
