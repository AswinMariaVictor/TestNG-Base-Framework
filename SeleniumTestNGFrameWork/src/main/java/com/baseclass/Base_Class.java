package com.baseclass;
import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Base_Class {

	public static WebDriver driver;
	public static String value; // read_Particular Excel data

// Launching the Browser
	public static WebDriver getBrowser(String browser) {
		if (browser.equalsIgnoreCase("Chrome")) {
			//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();   // Up casting
		} else if (browser.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\driver\\geckodriver\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		return driver;

	}

// Launching the Web site
	public static void getUrl(String url) {
		driver.get(url);
	}

//Drop Down 
	public static void select(WebElement element, String method, String input) {
		Select s = new Select(element);

		if (method.equalsIgnoreCase("index")) // Select by Index
		{
			int data = Integer.parseInt(input);
			s.selectByIndex(data);
		} else if (method.equalsIgnoreCase("value")) // Select by Value
		{
			s.selectByValue(input);
		} else if (method.equalsIgnoreCase("text")) // Select by Visible Text
		{
			s.selectByVisibleText(input);
		}

	}

	public static void deSelect(WebElement element, String method, String input) {
		Select s = new Select(element);

		if (method.equalsIgnoreCase("index")) // DeSelect by Index
		{
			int data = Integer.parseInt(input);
			s.deselectByIndex(data);
		} else if (method.equalsIgnoreCase("value")) // DeSelect by Value
		{
			s.deselectByValue(input);
		} else if (method.equalsIgnoreCase("text")) // DeSelect by Visible Text
		{
			s.deselectByVisibleText(input);
		}

	}

	public static void deSelectAll(WebElement element) {
		Select s = new Select(element);
		s.deselectAll();
	}

//Maximize
	public static void maximixe() {
		driver.manage().window().maximize();
	}

//Navigate to an Url
	public static void navigateTo(String url) {
		driver.navigate().to(url);
	}

//Forward
	public static void navigateForward() {
		driver.navigate().forward();
	}

//Back
	public static void navigateBack() {
		driver.navigate().back();
	}

//Refresh
	public static void navigateRefresh() {
		driver.navigate().refresh();
	}

//Alert 
	public static void alert(String type) {
		if (type.equalsIgnoreCase("accept")) {
			driver.switchTo().alert().accept();
		} else if (type.equalsIgnoreCase("dismiss")) {

			driver.switchTo().alert().dismiss();
		}
	}

	public static void promptAlert(String data) {
		driver.switchTo().alert().sendKeys(data);
		alert("accept");
	}

//Actions
	public static void actions(WebElement element, String type) {
		Actions a = new Actions(driver); // Parameterized Constructor
		if (type.equalsIgnoreCase("click")) {
			a.click(element).perform();
		} else if (type.equalsIgnoreCase("context click")) {
			a.contextClick(element).perform();
		} else if (type.equalsIgnoreCase("double click")) {
			a.doubleClick(element).perform();
		} else if (type.equalsIgnoreCase("move to element")) {
			a.moveToElement(element).perform();
		}

	}

	public static void dragAndDrop(WebElement source, WebElement target) {
		Actions a = new Actions(driver);
		a.dragAndDrop(source, target);
	}

//Frames
	public static void frames(String type, String value) {
		if (type.equalsIgnoreCase("id")) {
			driver.switchTo().frame(value);
		} else if (type.equalsIgnoreCase("index")) {
			int i = Integer.parseInt(value);
			driver.switchTo().frame(i);
		}
	}

	public static void framesParent() {
		driver.switchTo().parentFrame();
	}

	public static void framesDefault() {
		driver.switchTo().defaultContent();
	}

	public static void frames(WebElement element) {
		driver.switchTo().frame(element);
	}

//Robot
	public static void Robot(int key, int times) throws AWTException {
		Robot r = new Robot();
		for (int i = 0; i < times; i++) {
			r.keyPress(key);
			r.keyRelease(key);
		}
	}

//Window handles

	public static Set<String> getWindowsId() {
		return driver.getWindowHandles();
	}

	public static void getWindowsTitle() {
		Set<String> ids = getWindowsId();
		for (String id : ids) {
			String title = driver.switchTo().window(id).getTitle();
			System.out.println(title);
		}
	}

	public static void toWindowTitle(String title) {
		Set<String> ids = getWindowsId();
		for (String id : ids) {
			if (driver.switchTo().window(id).getTitle().equals(title)) {
				break;
			}
		}
	}

//check Box
	public static void checkBox(WebElement checkBox) {
		checkBox.click();
	}

//is Enabled
	public static boolean isEnable(WebElement element) {
		return element.isEnabled();
	}

//is Displayed
	public static boolean isDisplayed(WebElement element) {
		return element.isDisplayed();
	}

//is Selected
	public static boolean isSelected(WebElement element) {
		return element.isSelected();
	}

//is Multiple
	public static boolean isMultiple(WebElement element) {
		Select s = new Select(element);
		return s.isMultiple();
	}

//get All Options
	public static List<WebElement> getAllOptions(WebElement element) {
		Select s = new Select(element);
		return s.getOptions();

	}

//get First Selected Option
	public static WebElement getFirstselected(WebElement element) {
		Select s = new Select(element);
		return s.getFirstSelectedOption();
	}

//get All Selected Options
	public static List<WebElement> getAllSelected(WebElement element) {
		Select s = new Select(element);
		return s.getAllSelectedOptions();
	}

//get Title
	public static String getTitle() {
		return driver.getTitle();
	}

//get current URL
	public static String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

//get Text
	public static String getText(WebElement element) {
		return element.getText();
	}

//get Attribute	
	public static String getAttribute(WebElement element, String type) {
		return element.getAttribute(type);
	}

//static wait
	public static void staticWait(int time) throws InterruptedException {
		Thread.sleep(time);
	}

//Implicit/Global wait
	public static void implicitWait(Duration time) {
		driver.manage().timeouts().implicitlyWait(time);
	}

//Explicit wait
	public static void explicitWait(Duration time, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

//TakesScreenShot
	public static void TakesScreenShot(String path) throws IOException {
		TakesScreenshot sc = (TakesScreenshot) driver;  // Narrowing
		File src = sc.getScreenshotAs(OutputType.FILE);
		File dest = new File(path);
		FileUtils.copyFile(src, dest);
	}

//JavaScriptExecutor
	public static void scrollUpAndDown(int value) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," + value + ")", "");
	}

	public static void scrollToBottom() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)", "");
	}

	public static void scrollToTop() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,0)", "");
	}

	public static void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

//Read particular Excel data
//	public static String readParticularData(String path, int sheet_Index, int row_Index, int cell_Index)
//			throws IOException {
//		File f = new File(path);
//		FileInputStream fis = new FileInputStream(f);
//		Workbook wb = new XSSFWorkbook(fis);
//		Sheet sheet = wb.getSheetAt(sheet_Index);
//		Row row = sheet.getRow(row_Index);
//		Cell cell = row.getCell(cell_Index);
//		CellType cellType = cell.getCellType();
//		if (cellType.equals(cellType.STRING)) {
//			value = cell.getStringCellValue();
//		} else if (cellType.equals(cellType.NUMERIC)) {
//			double numericCellValue = cell.getNumericCellValue();
//			int data = (int) numericCellValue; // converting double to int
//			String value = String.valueOf(data); // converting int to string
//
//		}
//		return value;
//
//	}

//send keys()
	public static void inputValueElement(WebElement element, String value) {
		element.sendKeys(value);
	}

// close
	public static void close() {
		driver.close();
	}

//click()
	public static void clickOnElement(WebElement element) {
		element.click();
	}

//Radio button
	public static void radioButton(WebElement element) {
		element.click();
	}

// quit
	public static void quit() {
		driver.quit();
	}

}
