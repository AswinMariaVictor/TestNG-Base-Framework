package com.runner;

import org.openqa.selenium.WebDriver;

import com.baseclass.Base_Class;
import com.sdp.Page_Object_Manager;

public class Runner_Test extends Base_Class {

	public static WebDriver driver = getBrowser("chrome");

	public static Page_Object_Manager pom = new Page_Object_Manager(driver);

	public static void main(String[] args) {

		getUrl("https://testautomationpractice.blogspot.com/");
		maximixe();
		inputValueElement(pom.getHomePageObject().NameField(), "Aswin");
	}

}
