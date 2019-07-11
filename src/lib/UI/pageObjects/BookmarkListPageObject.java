package lib.UI.pageObjects;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class BookmarkListPageObject extends MainPageObject {

	protected static String
			ARTICLE_TITLE;

	public BookmarkListPageObject(AppiumDriver driver) {
		super(driver);
	}

	/* TEMPLATES METHODS */
	private static String getListItemElement(String substr) {
		return ARTICLE_TITLE.replace("{ARTICLE_TITLE}", substr);
	}

	/* TEMPLATES METHODS */

	public void deleteArticleFromListBySwipe(String articleTitle) {
		this.swipeElementToLeft(getListItemElement(articleTitle), "Cannot find '" + articleTitle + "'");
		if (Platform.getInstance().isIOS()){
			this.clickElementToTheRightUpperCorner(getListItemElement(articleTitle), "Cannot find saved article");
		}
	}

	public void openArticle(String articleTitle){
		this.waitForElementAndClick(getListItemElement(articleTitle), "There is no article with title '" + articleTitle + "' on the screen", 5);
	}

	public void checkArticlePresentInList(String atricleTitle){
		this.waitForElementPresent(getListItemElement(atricleTitle), "Atricle with name ' " + atricleTitle + " not present in list'");
	}

	public void checkArticleNotPresentInList(String atricleTitle){
		this.waitForElementNotPresent(getListItemElement(atricleTitle), "Atricle with name ' " + atricleTitle + " is present in list'", 5);
	}
}
