import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.pom.HomePage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserLaunch {

	public static void main(String[] args) throws InterruptedException {
		//String projectPath = System.getProperty("user.dir");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("Spark.html");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		ExtentTest test1 = extent.createTest("MyFirstTest");
	
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		test1.log(Status.INFO, "Initiating test case");
		driver.get("https://automationstepbystep.com/online-courses/");
		test1.pass("First navigation successful");
		
		Thread.sleep(5000);
		HomePage hp = new HomePage(driver);
		hp.NameField().sendKeys("Aswin");
		driver.navigate().to("https://www.google.co.in/");
		test1.fail("Second navigation failed");
		
		test1.info("Completed all actions");
		driver.close();
		test1.pass("Browser closed");
		
		extent.flush();
	}
}
