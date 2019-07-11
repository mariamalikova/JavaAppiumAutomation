package lib.UI.ios;

import io.appium.java_client.AppiumDriver;
import lib.UI.pageObjects.BookmarkListPageObject;

public class IOSBookmarkListPageObject extends BookmarkListPageObject {

	static {
		ARTICLE_TITLE = "xpath://XCUIElementTypeLink[contains(@name, '{ARTICLE_TITLE}')]";
	}

	public IOSBookmarkListPageObject(AppiumDriver driver){
		super(driver);
	}
}

