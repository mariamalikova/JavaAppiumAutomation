package lib.UI.pageObjects;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject {

	protected static String
			ARTICLE_MENU_BOOKMARK,
			GOT_IT_BUTTON,
			CREATE_NEW_LIST_BUTTON,
			LIST_TITLE_INPUT,
			CREATE_LIST_OK_BUTTON,
			OVERFLOW_MENU_BUTTON,
			OVERFLOW_FEED_BUTTON,
			NO_THANKS_BUTTON,
			ARTICLE_SUBTITLE,
			GO_BACK_BUTTON,
			CLOSE_DIALOG_BUTTON;


	public ArticlePageObject(AppiumDriver driver){
		super(driver);
	}

	/* TEMPLATES METHODS */
	private static String getSubtitleElement(String substr) {
		return ARTICLE_SUBTITLE.replace("{SUBTITLE}", substr);
	}

	/* TEMPLATES METHODS */


	public void addArticleToBookmarksAndCreateList(String list_name){
		this.waitForElementAndClick(ARTICLE_MENU_BOOKMARK, "Cannot find button to open article options", 5);
		this.waitForElementAndClick(GOT_IT_BUTTON, "Cannot find 'GOT IT' button", 5);
		this.waitForElementAndClick(CREATE_NEW_LIST_BUTTON, "Cannot find button to create a new reading list", 5);
		this.waitForElementAndSendKeys(LIST_TITLE_INPUT, list_name,"Cannot put text into articles folder name input", 5);
		this.waitForElementAndClick(CREATE_LIST_OK_BUTTON, "Cannot press OK button", 5);
	}

	public void addArticleToBookmarks(String list_name){
		this.pressByCoordinates();
		this.waitForElementAndClick(ARTICLE_MENU_BOOKMARK, "Cannot find button to open article options", 10);
		this.waitForElementAndClick("xpath://*[contains(@text, '" + list_name + "')]", "Cannot find folder " + list_name, 5);
	}

	public void returnToMainScreen(){

		this.waitForElementAndClick(OVERFLOW_MENU_BUTTON, "There is no menu button on the screen", 5);
		this.waitForElementAndClick(OVERFLOW_FEED_BUTTON, "There is no feed button on the screen", 5);
		this.waitForElementAndClick(NO_THANKS_BUTTON, "There is no 'no thanks' button on the screen", 10);
	}

	public String getArticleSubtitle(){
		WebElement subtitle_element = this.waitForElementPresent(ARTICLE_SUBTITLE,"There is no article subtitle on the screen",5);
		return subtitle_element.getText();
	}

	public void assertSubtitlePresent(){
		this.assertElementPresent(ARTICLE_SUBTITLE, "We don't found subtitle element");
	}

	public void addArticleToMySaved(){
		this.waitForElementAndClick(ARTICLE_MENU_BOOKMARK, "Cannot find button to open article options", 10);
	}

	public void closeArticle(){
		this.waitForElementAndClick(GO_BACK_BUTTON, "Cannot find Back button", 10);
	}

	public void closeDialog(){
		this.waitForElementAndClick(CLOSE_DIALOG_BUTTON, "Cannot find close dialog button", 10);
	}
}
