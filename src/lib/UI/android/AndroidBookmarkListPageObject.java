package lib.UI.android;

import io.appium.java_client.AppiumDriver;
import lib.UI.pageObjects.BookmarkListPageObject;

public class AndroidBookmarkListPageObject extends BookmarkListPageObject {

	static {
		ARTICLE_TITLE = "xpath://*[contains(@text, '{ARTICLE_TITLE}')]";
	}

	public AndroidBookmarkListPageObject(AppiumDriver driver){
		super(driver);
	}
}
