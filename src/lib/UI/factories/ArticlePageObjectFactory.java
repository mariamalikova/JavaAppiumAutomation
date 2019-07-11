package lib.UI.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.UI.android.AndroidArticlePageObject;
import lib.UI.ios.IOSArticlePageObject;
import lib.UI.pageObjects.ArticlePageObject;

public class ArticlePageObjectFactory {

	public static ArticlePageObject get(AppiumDriver driver){

		if(Platform.getInstance().isAndroid()) {
			return new AndroidArticlePageObject(driver);
		} else {
			return new IOSArticlePageObject(driver);
		}
	}
}
