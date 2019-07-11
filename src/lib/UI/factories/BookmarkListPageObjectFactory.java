package lib.UI.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.UI.android.AndroidBookmarkListPageObject;
import lib.UI.ios.IOSBookmarkListPageObject;
import lib.UI.pageObjects.BookmarkListPageObject;

public class BookmarkListPageObjectFactory {

	public static BookmarkListPageObject get(AppiumDriver driver){

		if(Platform.getInstance().isAndroid()) {
			return new AndroidBookmarkListPageObject(driver);
		} else {
			return new IOSBookmarkListPageObject(driver);
		}
	}
}
