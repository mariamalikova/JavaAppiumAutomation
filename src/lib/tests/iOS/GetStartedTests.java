package lib.tests.iOS;

import lib.CoreTestCase;
import lib.UI.WelcomePageObject;
import org.junit.Test;

public class GetStartedTests extends CoreTestCase {

	@Test
	public void testPassThroughWelcome(){

		WelcomePageObject welcomePageObject = new WelcomePageObject(driver);

		welcomePageObject.waitForLearnMoreLink();
		welcomePageObject.clickNextButton();
		welcomePageObject.waitForNewWaysText();
		welcomePageObject.clickNextButton();
		welcomePageObject.waitForSearchInText();
		welcomePageObject.clickNextButton();
		welcomePageObject.waitForHelpMakeText();
		welcomePageObject.clickGetStartedButton();

	}
}
