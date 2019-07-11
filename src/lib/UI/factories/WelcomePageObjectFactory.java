package lib.UI.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.UI.android.AndroidWelcomePageObject;
import lib.UI.ios.IOSWelcomePageObject;
import lib.UI.pageObjects.WelcomePageObject;

public class WelcomePageObjectFactory {

	public static WelcomePageObject get(AppiumDriver driver){

		if(Platform.getInstance().isAndroid()) {
			return new AndroidWelcomePageObject(driver);
		} else {
			return new IOSWelcomePageObject(driver);
		}
	}
}
