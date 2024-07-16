package com.test.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNG_Demo2 {

	private WebDriver driver;
	private ExtentSparkReporter sparkReporter;
	private ExtentReports extent;
	private ExtentTest test;
	private ChromeOptions options;

	private TestNG_Demo2()
	{
		options = new ChromeOptions();
		sparkReporter = new ExtentSparkReporter("TestNG_Demo.html");
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
	}

	@BeforeSuite
	public void testFixtureInitialization() {

		WebDriverManager.chromedriver().setup();
		options.setCapability("", false);
		driver = new ChromeDriver();
	}

	@BeforeTest
	public void testInitialization()
	{
		ExtentTest test = extent.createTest("Before Test");
		test.info("Launch the application");
		driver.manage().window().maximize();
		driver.get("https://testautomationpractice.blogspot.com/");
		test.pass("Application launch successful");
	}

	@Test
	public void firstTestMethod2()
	{
		test = extent.createTest("MyFirstTest");
		test.info("Initiating first test method");
		driver.findElement(By.id("name")).sendKeys("Aswin");;
		test.pass("First completed");
	}

	@Test
	public void secondMethod2()
	{
		test = extent.createTest("MySecondTest");
		test.info("Initiating second test Method");
		System.out.println(driver.findElement(By.id("name")).getText());
		test.pass("Second completed");
	}

	@AfterTest
	public void testClosure()
	{
		test = extent.createTest("Test closure");
		driver.quit();
	}

	@AfterSuite
	public void testFixtureClosure()
	{
		test = extent.createTest("Fixture closure");
		extent.flush();
	}

}
