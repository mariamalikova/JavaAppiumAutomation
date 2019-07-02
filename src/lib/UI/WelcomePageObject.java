package lib.UI;

import io.appium.java_client.AppiumDriver;

public class WelcomePageObject extends MainPageObject{

	private static final String
			LEARN_MORE_LINK = "xpath://*[@name ='Learn more about Wikipedia']",
			NEW_WAYS_TO_EXPLORE_TEXT = "xpath://*[@name ='New ways to explore']",
			SEARCH_IN_TEXT = "xpath://*[@name ='Search in nearly 300 languages']",
			HELP_TO_MAKE_APP_BETTER_TEXT = "xpath://*[@name ='Help make the app better']",
			GET_STARTED_BUTTON = "xpath://*[@name ='Get started']",
			NEXT_BUTTON = "xpath://*[@name ='Next']";

	public WelcomePageObject(AppiumDriver driver){
		super(driver);
	}

	public void waitForLearnMoreLink(){
		this.waitForElementPresent(LEARN_MORE_LINK, "Cannot find 'Learn more about Wikipedia' link", 10);
	}

	public void waitForNewWaysText(){
		this.waitForElementPresent(NEW_WAYS_TO_EXPLORE_TEXT, "Cannot find 'New ways to explore' text", 10);
	}

	public void waitForSearchInText(){
		this.waitForElementPresent(SEARCH_IN_TEXT, "Cannot find 'Search in nearly 300 languages' text", 10);
	}

	public void waitForHelpMakeText(){
		this.waitForElementPresent(HELP_TO_MAKE_APP_BETTER_TEXT, "Cannot find 'Help make the app better' text", 10);
	}

	public void clickNextButton(){
		this.waitForElementAndClick(NEXT_BUTTON, "Cannot find and click 'Next' link", 10);
	}

	public void clickGetStartedButton(){
		this.waitForElementAndClick(GET_STARTED_BUTTON, "Cannot find and click 'Get started' button", 10);
	}
}
