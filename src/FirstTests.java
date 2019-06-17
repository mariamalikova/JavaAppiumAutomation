import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTests {


	private AppiumDriver driver;

	@Before
	public void setUp() throws Exception{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName","Android");
		capabilities.setCapability("deviceName","AndroidTestDevice");
		capabilities.setCapability("platformVersion","8.0");
		capabilities.setCapability("automationName","Appium");
		capabilities.setCapability("appPackage","org.wikipedia");
		capabilities.setCapability("appActivity",".main.MainActivity");
		capabilities.setCapability("app","/Volumes/Disk/Work/JavaAppiumAutomation/apks/org.wikipedia_10280_apps.evozi.com.apk");

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

		waitForElementAndClick(
				By.xpath("//*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
				"There is no skip button on screen",
				5
		);
	}

	@After
	public void tearDown(){
		driver.quit();
	}

	// Ex 2
	@Test
	public void checkSearchField(){
		WebElement search_field = waitForElementPresent(
				By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
				"There is no search field on the screen"
		);
	}

	// Ex 3
	@Test
	public void checkSearchResultPresent(){
		waitForElementAndClick(
				By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
				"There is no search field on the screen",
				5
		);

		waitForElementAndSendKeys(
				By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
				"Java",
				"There is no search field on the screen",
				5
		);

		waitForElementPresent(
				By.xpath("//*[@resource-id='org.wikipedia:id/fragment_search_results']"),
				"There is no search results on the screen"
		);

		List<WebElement> search_results = waitForListOfElementsPresentByXPath(
				By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
				"There is no search results on the screen",
				5
		);

		Assert.assertTrue("There is no search results on the screen", search_results.size()>0);

		waitForElementAndClear(
				By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
				"There is no search field on the screen",
				5
		);

		waitForElementNotPresent(
				By.xpath("//*[@resource-id='org.wikipedia:id/fragment_search_results']"),
				"Search results are present on the screen",
				5
		);
	}

	// Ex 4
	@Test
	public void checkSearchResultContainsRequestedString(){
		waitForElementAndClick(
				By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
				"There is no searchfieald on the screen",
				5
		);

		waitForElementAndSendKeys(
				By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
				"Java",
				"There is no search field on the screen",
				5
		);

		List<WebElement> search_results = waitForListOfElementsPresentByXPath(
				By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
				"There is no search results on the screen",
				5
		);

		for (WebElement we : search_results){
			Assert.assertTrue("Result does not contains search world", we.getText().toLowerCase().contains("java"));
		}
	}

	//------------------------------------------------------------------------------------------------------------------
	// Search and click
	//------------------------------------------------------------------------------------------------------------------


	private WebElement waitForElementPresent(By by, String error_msg, long timeoutInSeconds){
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		wait.withMessage(error_msg + "\n");
		return wait.until(
				ExpectedConditions.presenceOfElementLocated(by)
		);
	}

	private WebElement waitForElementPresent(By by, String error_msg){
		return waitForElementPresent(by, error_msg, 5);
	}

	private WebElement waitForElementAndClick(By by, String error_msg, long timeoutInSeconds){
		WebElement element = waitForElementPresent(by, error_msg);
		element.click();
		return element;
	}

	private WebElement waitForElementAndSendKeys(By by, String value, String error_msg, long timeoutInSeconds){
		WebElement element = waitForElementPresent(by, error_msg, timeoutInSeconds);
		element.sendKeys(value);
		return element;
	}

	private  WebElement waitForElementAndClear(By by, String error_msg, long timeoutInSeconds){
		WebElement element = waitForElementPresent(by, error_msg, timeoutInSeconds);
		element.clear();
		return element;
	}

	private boolean waitForElementNotPresent(By by, String error_msg, long timeoutInSeconds){
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		wait.withMessage(error_msg + "\n");
		return wait.until(
				ExpectedConditions.invisibilityOfElementLocated(by)
		);
	}

	private List<WebElement> waitForListOfElementsPresentByXPath(By by, String error_msg, long timeoutInSeconds){
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		wait.withMessage(error_msg + "\n");
		return wait.until(
				ExpectedConditions.presenceOfAllElementsLocatedBy(by)
		);
	}
}

