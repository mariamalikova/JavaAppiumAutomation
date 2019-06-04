import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

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

	@Test
	public void checkSearchField(){
		WebElement search_field = waitForElementPresent(
				By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
				"There is no search field on the screen"
		);
	}



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

}

