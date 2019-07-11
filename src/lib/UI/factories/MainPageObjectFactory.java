package lib.UI.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.UI.android.AndroidMainPageObject;
import lib.UI.ios.IOSMainPageObject;
import lib.UI.pageObjects.MainPageObject;

public class MainPageObjectFactory {

	public static MainPageObject get(AppiumDriver driver){

		if(Platform.getInstance().isAndroid()) {
			return new AndroidMainPageObject(driver);
		} else {
			return new IOSMainPageObject(driver);
		}
	}
}
