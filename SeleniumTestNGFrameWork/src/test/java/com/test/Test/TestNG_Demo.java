package com.test.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNG_Demo {

	private WebDriver driver;
	private ExtentSparkReporter sparkReporter;
	private ExtentReports extent;
	private ExtentTest test;
	private ChromeOptions options;
	private SoftAssert soft_assert;

	private TestNG_Demo() {
		options = new ChromeOptions();
		sparkReporter = new ExtentSparkReporter("TestNG_Demo.html");
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		soft_assert = new SoftAssert();
	}

	@BeforeSuite
	public void testFixtureInitialization() {

		WebDriverManager.chromedriver().setup();
		options.setCapability("", false);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void testInitialization() {
		ExtentTest test = extent.createTest("Before Test");
		test.info("Launch the application");
		driver.get("https://testautomationpractice.blogspot.com/");
		test.pass("Application launch successful");
	}

	@Test(priority = 1, groups = { "sanity", "smoke" }, timeOut = 120000) // timeout can be set at suite level and will
																			// be applicable to all the methods in the
																			// suite. The run should be completed within
																			// the given time. Else will be marked as
																			// failed.
	public void firstTestMethod() {
		test = extent.createTest("MyFirstTest");
		test.info("Initiating first test method");
		test.pass("First completed");
		System.out.println("First test method executed");
	}

	@Test(priority = 2, groups = "sanity", dependsOnMethods = "firstTestMethod") // After grouping we need to "include"
																					// that in the testng.xml file.
	@Parameters({ "textToBeReplaced" }) // Pass values as parameters. The value should be given in the testng.xml file.
	public void secondMethod(@Optional("Optional text is replaced") String textToBeReplaced) // Declare the return type
																								// of the variable.
																								// If run at class level
																								// then optional value
																								// will be replaced. If
																								// run at testng.xml
																								// file then that value
																								// will be replaced
	{
		test = extent.createTest("MySecondTest");
		test.info("Initiating second test Method");
		System.out.println("The replaced text: " + textToBeReplaced); // Using the variable.
		test.pass("Second completed");
	}

	@Test(priority = 3, dependsOnGroups = "sanity", invocationCount = 2) // An invocationCount in TestNG is the number
																			// of times that we want to execute the same
																			// test.
	public void thirdTestMethod() {
		test = extent.createTest("MyThirdTest");
		test.info("Initiating third test Method");
		test.pass("Third completed");
	}

	@Test(priority = 4, enabled = true) // enabled is used to skip a test method if it is set as "false". By default it
										// will be true
	public void fourthTestMethod() {
		test = extent.createTest("MyFourthTest");
		test.info("Initiating fourth test Method");
		test.pass("fourth completed");
		soft_assert.assertTrue(false, "It is a soft assert"); // Soft assert will not terminate the execution
		Assert.assertTrue(false, "It is a hard assert"); // Hard assert will terminate the execution
		System.out.println("executed after hard assert"); // This line will not be executed since it is placed after the
															// hard assert. And we are making the hard assert fail
															// willingly.
	}

	@AfterMethod
	public void testClosure() {
		test = extent.createTest("Test closure");
	}

	@AfterSuite
	public void testFixtureClosure() {
		test = extent.createTest("Fixture closure");
		driver.quit();
		extent.flush();
	}

}
