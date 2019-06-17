import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTests {


	private AppiumDriver driver;

	@Before
	public void setUp() throws Exception{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName","Android");
		capabilities.setCapability("deviceName","AndroidTestDevice");
		capabilities.setCapability("platformVersion","8.0");
		capabilities.setCapability("automationName","Appium");
		capabilities.setCapability("appPackage","org.wikipedia");
		capabilities.setCapability("appActivity",".main.MainActivity");
		capabilities.setCapability("app","/Volumes/Disk/Work/JavaAppiumAutomation/apks/org.wikipedia_10280_apps.evozi.com.apk");

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

		driver.rotate(ScreenOrientation.PORTRAIT);

		waitForElementAndClick(
				By.xpath("//*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']"),
				"There is no skip button on screen",
				5
		);
	}

	@After
	public void tearDown(){
		driver.quit();
	}

	// Ex 2
	@Test
	public void checkSearchField(){
		WebElement search_field = waitForElementPresent(
				By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
				"There is no search field on the screen"
		);
	}

	// Ex 3
	@Test
	public void checkSearchResultPresent(){
		waitForElementAndClick(
				By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
				"There is no search field on the screen",
				5
		);

		waitForElementAndSendKeys(
				By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
				"Java",
				"There is no search field on the screen",
				5
		);

		waitForElementPresent(
				By.xpath("//*[@resource-id='org.wikipedia:id/fragment_search_results']"),
				"There is no search results on the screen"
		);

		List<WebElement> search_results = waitForListOfElementsPresentByXPath(
				By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
				"There is no search results on the screen",
				5
		);

		Assert.assertTrue("There is no search results on the screen", search_results.size()>0);

		waitForElementAndClear(
				By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
				"There is no search field on the screen",
				5
		);

		waitForElementNotPresent(
				By.xpath("//*[@resource-id='org.wikipedia:id/fragment_search_results']"),
				"Search results are present on the screen",
				5
		);
	}

	// Ex 4
	@Test
	public void checkSearchResultContainsRequestedString(){
		waitForElementAndClick(
				By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
				"There is no searchfieald on the screen",
				5
		);

		waitForElementAndSendKeys(
				By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
				"Java",
				"There is no search field on the screen",
				5
		);

		List<WebElement> search_results = waitForListOfElementsPresentByXPath(
				By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']"),
				"There is no search results on the screen",
				5
		);

		for (WebElement we : search_results){
			Assert.assertTrue("Result does not contains search world", we.getText().toLowerCase().contains("java"));
		}
	}

	// Ex 5
	@Test
	public void saveTwoArticlesToMyList() {

		String folder_name = "Learning programming";
		String first_article_title = "Java";
		String second_article_title = "Kotlin";


		waitForElementAndClick(
				By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
				"There is no search field on the screen",
				5
		);

		waitForElementAndSendKeys(
				By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
				first_article_title,
				"There is no search field on the screen",
				5
		);
		waitForElementPresent(
				By.xpath("//*[@resource-id='org.wikipedia:id/fragment_search_results']"),
				"There is no search results on the screen"
		);

		waitForElementAndClick(
				By.xpath("//*[contains(@text, 'programming language')]"),
				"There is no '"+ first_article_title +"' search results on the screen",
				5
		);

		waitForElementAndClick(
				By.xpath("//*[@resource-id='org.wikipedia:id/article_menu_bookmark']"),
				"Cannot find button to open article options",
				5
		);

		waitForElementAndClick(
				By.xpath("//*[@text='GOT IT']"),
				"Cannot find 'GOT IT' button",
				5
		);

		waitForElementAndClick(
				By.xpath("//*[@text='Create new']"),
				"Cannot find button to create a new reading list",
				5
		);

		waitForElementAndSendKeys(
				By.xpath("//*[@resource-id='org.wikipedia:id/text_input']"),
				folder_name,
				"Cannot put text into articles folder name input",
				5
		);
		waitForElementAndClick(
				By.xpath("//*[@text= 'OK']"),
				"Cannot press OK button",
				5
		);

		waitForElementAndClick(
				By.xpath("//*[@resource-id='org.wikipedia:id/page_toolbar_button_show_overflow_menu']"),
				"There is no menu button on the screen",
				5
		);

		waitForElementAndClick(
				By.xpath("//*[@resource-id='org.wikipedia:id/page_action_overflow_feed']"),
				"There is no feed button on the screen",
				5
		);

		waitForElementAndClick(
				By.xpath("//*[@resource-id='android:id/button2']"),
				"There is no 'no thanks' button on the screen",
				5
		);

		waitForElementAndClick(
				By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
				"There is no search field on the screen",
				5
		);

		waitForElementAndSendKeys(
				By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
				second_article_title,
				"There is no search field on the screen",
				5
		);
		waitForElementPresent(
				By.xpath("//*[@resource-id='org.wikipedia:id/fragment_search_results']"),
				"There is no search results on the screen"
		);
		waitForElementAndClick(
				By.xpath("//*[contains(@text, 'programming language')]"),
				"There is no '"+ second_article_title +"' search results on the screen",
				5
		);
		waitForElementAndClick(
				By.xpath("//*[@resource-id='org.wikipedia:id/article_menu_bookmark']"),
				"Cannot find button to open article options",
				5
		);

		waitForElementAndClick(
				By.xpath("//*[contains(@text, '" + folder_name + "')]"),
				"Cannot find folder " + folder_name,
				5
		);

		waitForElementAndClick(
				By.xpath("//*[@resource-id='org.wikipedia:id/page_toolbar_button_show_overflow_menu']"),
				"There is no menu button on the screen",
				5
		);

		waitForElementAndClick(
				By.xpath("//*[@resource-id='org.wikipedia:id/page_action_overflow_feed']"),
				"There is no feed button on the screen",
				5
		);

		waitForElementAndClick(
				By.xpath("//*[@resource-id='android:id/button2']"),
				"There is no 'no thanks' button on the screen",
				5
		);

		waitForElementAndClick(
				By.xpath("//*[@content-desc='My lists']"),
				"There is no 'My lists' button on the screen",
				5
		);

		waitForElementAndClick(
				By.xpath("//*[contains(@text, '" + folder_name + "')]"),
				"There is no '" + folder_name + "' folder on the screen",
				5
		);

		swipeElementToLeft(
				By.xpath("//*[contains(@text, '" + second_article_title + "')]"),
				"Cannot find '" + second_article_title + "'"
		);

		waitForElementNotPresent(
				By.xpath("//*[contains(@text, '" + second_article_title + "')]"),
				"There is no search field on the screen",
				5
		);

		waitForElementAndClick(
				By.xpath("//*[contains(@text, '" + first_article_title + "')]"),
				"There is no search field on the screen",
				5
		);

		WebElement title_element = waitForElementPresent(
				By.xpath("//*[contains(@text, '" + first_article_title + "')]"),
				"There is no search field on the screen",
				5
		);

		Assert.assertTrue("Title does not match. Expected: " + first_article_title + ", actual: " + title_element.getText(), first_article_title.equals(title_element.getText()));

	}

	// Ex 6
	@Test
	public void checkArticleTitleWithoutWaiting(){
		waitForElementAndClick(
				By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
				"There is no search field on the screen",
				5
		);

		waitForElementAndSendKeys(
				By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
				"Java",
				"There is no search field on the screen",
				5
		);
		waitForElementPresent(
				By.xpath("//*[@resource-id='org.wikipedia:id/fragment_search_results']"),
				"There is no search results on the screen"
		);

		waitForElementAndClick(
				By.xpath("//*[contains(@text, 'programming language')]"),
				"There is no 'Java (programming language')' search results on the screen",
				5
		);

		assertElementPresent(
				By.xpath("//*[@resource-id='pagelib_edit_section_title_description']"),
				"We don't found subtitle element");
	}

	// Ex 7
	@Test
	public void testChangeScreenOrientationOnSearchResults() {
		waitForElementAndClick(
				By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
				"There is no search field on the screen",
				5
		);

		waitForElementAndSendKeys(
				By.xpath("//*[@resource-id='org.wikipedia:id/search_src_text']"),
				"Java",
				"There is no search field on the screen",
				5
		);

		waitForElementAndClick(
				By.xpath("//*[contains(@text, 'programming language')]"),
				"There is no 'programming language' search results on the screen",
				15
		);


		String title_before_rotation = waitForElementAndGetText(
				By.xpath("//*[@resource-id='pagelib_edit_section_title_description']"),
				"Cannot Find article title",
				15
		);

		driver.rotate(ScreenOrientation.LANDSCAPE);

		String title_after_rotation = waitForElementAndGetText(
				By.xpath("//*[@resource-id='pagelib_edit_section_title_description']"),
				"Cannot Find article title",
				15
		);

		Assert.assertEquals("Article title have been changed after rotation", title_after_rotation, title_before_rotation);
	}

	//------------------------------------------------------------------------------------------------------------------
	// Search and click
	//------------------------------------------------------------------------------------------------------------------

	private int getAmountOfElements(By by){
		List elements = driver.findElements(by);
		return elements.size();
	}

	private void assertElementPresent(By by, String err_msg){
		int amount_of_elements = getAmountOfElements(by);
		if (amount_of_elements == 0) {
			String default_message = "An element " + by.toString() + " supposed to be present";
			throw new AssertionError(default_message + " " + err_msg);

		}
	}

	private WebElement waitForElementPresent(By by, String error_msg, long timeoutInSeconds){
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		wait.withMessage(error_msg + "\n");
		return wait.until(
				ExpectedConditions.presenceOfElementLocated(by)
		);
	}

	private WebElement waitForElementPresent(By by, String error_msg){
		return waitForElementPresent(by, error_msg, 5);
	}

	private WebElement waitForElementAndClick(By by, String error_msg, long timeoutInSeconds){
		WebElement element = waitForElementPresent(by, error_msg);
		element.click();
		return element;
	}

	private WebElement waitForElementAndSendKeys(By by, String value, String error_msg, long timeoutInSeconds){
		WebElement element = waitForElementPresent(by, error_msg, timeoutInSeconds);
		element.sendKeys(value);
		return element;
	}

	private  WebElement waitForElementAndClear(By by, String error_msg, long timeoutInSeconds){
		WebElement element = waitForElementPresent(by, error_msg, timeoutInSeconds);
		element.clear();
		return element;
	}

	private boolean waitForElementNotPresent(By by, String error_msg, long timeoutInSeconds){
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		wait.withMessage(error_msg + "\n");
		return wait.until(
				ExpectedConditions.invisibilityOfElementLocated(by)
		);
	}

	private List<WebElement> waitForListOfElementsPresentByXPath(By by, String error_msg, long timeoutInSeconds){
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		wait.withMessage(error_msg + "\n");
		return wait.until(
				ExpectedConditions.presenceOfAllElementsLocatedBy(by)
		);
	}

	private String waitForElementAndGetText(By by, String err_msg, long timeoutInSeconds){
		WebElement element = waitForElementPresent(by, err_msg, timeoutInSeconds);
		return element.getText();
	}

	//------------------------------------------------------------------------------------------------------------------
	// Swipe
	//------------------------------------------------------------------------------------------------------------------


	protected void swipeUp(int timeOfSwipe){
		TouchAction action = new TouchAction(driver);
		Dimension size = driver.manage().window().getSize();
		int x = size.width/2;
		int start_y = (int) (size.height * 0.8);
		int end_y = (int) (size.height * 0.2);
		action
				.press(x, start_y)
				.waitAction(timeOfSwipe)
				.moveTo(x, end_y)
				.release()
				.perform();
	}

	protected void swipeUpQuick(){
		swipeUp(200);
	}

	protected void swipeUpToFindElement(By by, String error_msg, int max_swipes){

		int swipes_cnt = 0;
		while (driver.findElements(by).size() == 0){

			if (swipes_cnt > max_swipes){
				waitForElementPresent(by, "Cannot find element by swiping up. \n" + error_msg, 0);
				return;
			}
			swipeUpQuick();
			swipes_cnt++;
		}

	}

	protected void swipeElementToLeft(By by, String error_message)
	{
		WebElement element = waitForElementPresent(
				by,
				error_message
		);
		int left_x = element.getLocation().getX();
		int right_x = left_x + element.getSize().getWidth();
		int lower_y = element.getLocation().getY();
		int upper_y = lower_y + element.getSize().getHeight();
		int middle_y = (upper_y + lower_y) / 2;
		TouchAction action = new TouchAction(driver);
		action
				.press(right_x, middle_y)
				.waitAction(300)
				.moveTo(left_x, middle_y)
				.release()
				.perform();
	}
}

