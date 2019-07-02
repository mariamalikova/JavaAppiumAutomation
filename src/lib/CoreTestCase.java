package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import lib.UI.MainPageObject;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase extends TestCase {

	private static final String PLATFORM_IOS = "ios";
	private static final String PLATFORM_ANDROID = "android";

	protected AppiumDriver driver;
	private static String appiumUrl = "http://127.0.0.1:4723/wd/hub";
	private MainPageObject mainPAgeObject;

	@Override
	protected void setUp() throws Exception{
		super.setUp();
		DesiredCapabilities capabilities = this.getCapabilitiesByPlatformEnv();
		driver = getDriverByPlatformEnv(capabilities);
		rotateScreenPortrait();
	}

	private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception{
		String platform = System.getenv("PLATFORM");
		DesiredCapabilities capabilities = new DesiredCapabilities();

		if (platform.equals(PLATFORM_ANDROID)) {
			capabilities.setCapability("platformName","Android");
			capabilities.setCapability("deviceName","AndroidTestDevice");
			capabilities.setCapability("platformVersion","8.0");
			capabilities.setCapability("automationName","Appium");
			capabilities.setCapability("appPackage","org.wikipedia");
			capabilities.setCapability("appActivity",".main.MainActivity");
			capabilities.setCapability("app","/Volumes/Disk/Work/JavaAppiumAutomation/apks/org.wikipedia_10280_apps.evozi.com.apk");
		} else if (platform.equals(PLATFORM_IOS)) {
			capabilities.setCapability("platformName","iOS");
			capabilities.setCapability("deviceName","iPhone X");
			capabilities.setCapability("platformVersion","11.4");
			capabilities.setCapability("automationName","Appium");
			capabilities.setCapability("appPackage","org.wikipedia");
			capabilities.setCapability("appActivity",".main.MainActivity");
			capabilities.setCapability("app","/Volumes/Disk/Work/JavaAppiumAutomation/apks/Wikipedia.app");
		}
		else {
			throw new Exception("Cannot get run platform from env variable. Platform value " + platform);
		}
		return  capabilities;
	}

	private AppiumDriver getDriverByPlatformEnv(DesiredCapabilities capabilities) throws Exception{

		String platform = System.getenv("PLATFORM");

		switch (platform) {
			case PLATFORM_ANDROID:
				return new AndroidDriver(new URL(appiumUrl), capabilities);
			case PLATFORM_IOS:
				return new IOSDriver(new URL(appiumUrl), capabilities);
			default:
				throw new Exception("Cannot get run platform from env variable. Platform value " + platform);
		}
	}

	@Override
	protected void tearDown() throws Exception {
		driver.quit();

		super.tearDown();
	}

	protected void rotateScreenPortrait(){
		driver.rotate(ScreenOrientation.PORTRAIT);
	}

	protected void rotateScreenLandscape(){
		driver.rotate(ScreenOrientation.LANDSCAPE);
	}

}
