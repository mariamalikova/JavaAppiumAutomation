package lib.UI;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

	private static final String
			ARTICLE_MENU_BOOKMARK = "xpath://*[@resource-id='org.wikipedia:id/article_menu_bookmark']",
			GOT_IT_BUTTON = "xpath://*[@text='GOT IT']",
			CREATE_NEW_LIST_BUTTON = "xpath://*[@text='Create new']",
			LIST_TITLE_INPUT = "xpath://*[@resource-id='org.wikipedia:id/text_input']",
			CREATE_LIST_OK_BUTTON = "xpath://*[@text='OK']",
			OVERFLOW_MENU_BUTTON = "xpath://*[@resource-id='org.wikipedia:id/page_toolbar_button_show_overflow_menu']",
			OVERFLOW_FEED_BUTTON = "xpath://*[@resource-id='org.wikipedia:id/page_action_overflow_feed']",
			NO_THANKS_BUTTON = "xpath://*[@resource-id='android:id/button2']",
			ARTICLE_SUBTITLE = "xpath://*[@resource-id='pagelib_edit_section_title_description']";


	public ArticlePageObject(AppiumDriver driver){
		super(driver);
	}

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

}
