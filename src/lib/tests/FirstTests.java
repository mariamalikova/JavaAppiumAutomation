package lib.tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.UI.factories.*;
import lib.UI.pageObjects.*;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FirstTests extends CoreTestCase {

	private MainPageObject mainPAgeObject;

	protected void setUp() throws Exception{
		super.setUp();
	}

	// Ex 2
	@Test
	public void testCheckSearchField(){

		SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

		searchPageObject.initSearchInput();
		searchPageObject.typeSearchLine("Java");
		searchPageObject.waitForSearchResult("Object-oriented programming language");
	}

	// Ex 3
	@Test
	public void testCheckSearchResultPresent(){

		SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

		searchPageObject.initSearchInput();
		searchPageObject.typeSearchLine("Java");

		int resultCount = searchPageObject.getSearchResultCount();
		Assert.assertTrue("There is no search results on the screen", resultCount>0);

		searchPageObject.clickSearchCloseButton();
		searchPageObject.waitForSearchResultToDisappear();
	}

	// Ex 4
	@Test
	public void testCheckSearchResultContainsRequestedString(){

		SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
		String searchString = "java";

		searchPageObject.initSearchInput();
		searchPageObject.typeSearchLine(searchString);

		List<WebElement> search_results = searchPageObject.getSearchResult();

		for (WebElement we : search_results){
			Assert.assertTrue("Result does not contains search world", we.getText().toLowerCase().contains(searchString));
		}
	}

	// Ex 5
	// EX 11
	@Test
	public void testSaveTwoArticlesToMyList() {

		String folderName = "Learning programming";
		String firstArticleTitle = "Java";
		String firstArticleSubtitle = "Object-oriented programming language";
		String secondArticleTitle = "Kotlin";

		SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
		ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
		BookmarkListPageObject bookmarkPageObject = BookmarkListPageObjectFactory.get(driver);
		mainPAgeObject = MainPageObjectFactory.get(driver);

		searchPageObject.initSearchInput();
		searchPageObject.typeSearchLine(firstArticleTitle);
		searchPageObject.clickForSearchResult("Object-oriented programming language");

		if (Platform.getInstance().isAndroid()){
			articlePageObject.addArticleToBookmarksAndCreateList(folderName);
			articlePageObject.returnToMainScreen();
		} else if (Platform.getInstance().isIOS()){
			articlePageObject.addArticleToMySaved();
			articlePageObject.closeDialog();
			articlePageObject.closeArticle();
		}

		searchPageObject.initSearchInput();
		searchPageObject.typeSearchLine(secondArticleTitle);
		searchPageObject.clickForSearchResult("programming language");


		if (Platform.getInstance().isAndroid()){
			articlePageObject.addArticleToBookmarks(folderName);
			articlePageObject.returnToMainScreen();
			mainPAgeObject.openBookmarks();
			mainPAgeObject.openMyBookmarksList(folderName);
		} else if (Platform.getInstance().isIOS()){
			articlePageObject.addArticleToMySaved();
			articlePageObject.closeArticle();
			mainPAgeObject.openBookmarks();
		}

		bookmarkPageObject.deleteArticleFromListBySwipe(secondArticleTitle);

		if (Platform.getInstance().isAndroid()){
			bookmarkPageObject.openArticle(firstArticleTitle);
			String articleSubtitle = articlePageObject.getArticleSubtitle();
			Assert.assertEquals("Title does not match. Expected: " + firstArticleSubtitle + ", actual: " + articleSubtitle, firstArticleSubtitle, articleSubtitle);
		} else if (Platform.getInstance().isIOS()) {
			bookmarkPageObject.checkArticlePresentInList(firstArticleTitle);
			bookmarkPageObject.checkArticleNotPresentInList(secondArticleTitle);
		}
	}

	// Ex 6
	@Test
	public void testCheckArticleTitleWithoutWaiting(){

		SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
		ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
		String search_str = "java";

		searchPageObject.initSearchInput();
		searchPageObject.typeSearchLine(search_str);
		searchPageObject.clickForSearchResult("programming language");
		articlePageObject.assertSubtitlePresent();
	}

	// Ex 7
	@Test
	public void testChangeScreenOrientationOnSearchResults() {

		SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
		ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

		searchPageObject.initSearchInput();
		searchPageObject.typeSearchLine("Java");
		searchPageObject.clickForSearchResult("Object-oriented programming language");

		String articleSubtitleBeforeRotation = articlePageObject.getArticleSubtitle();

		this.rotateScreenLandscape();

		String articleSubtitleAfterRotation = articlePageObject.getArticleSubtitle();

		Assert.assertEquals("Article title have been changed after rotation", articleSubtitleAfterRotation, articleSubtitleBeforeRotation);
	}

	// Ex 9
	@Test
	public void testCheckSearchResultByTitleAndSubtitle(){

		SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

		String expecedTitle1 = "Java";
		String expectedSubtitle1 = "Island of Indonesia";
		String expecedTitle2 = "JavaScript";
		String expectedSubtitle2 = "Programming language";
		String expecedTitle3 = "Java (programming language)";
		String expectedSubtitle3 = "Object-oriented programming language";
		String searchRequest = "Java";

		searchPageObject.initSearchInput();
		searchPageObject.typeSearchLine(searchRequest);

		searchPageObject.waitForElementByTitleAndDescription(expecedTitle1, expectedSubtitle1);
		searchPageObject.waitForElementByTitleAndDescription(expecedTitle2, expectedSubtitle2);
		searchPageObject.waitForElementByTitleAndDescription(expecedTitle3, expectedSubtitle3);
	}

	@Test
	public void testPassThroughWelcome(){

		if (Platform.getInstance().isAndroid()){
			return;
		}

		WelcomePageObject welcomePageObject = WelcomePageObjectFactory.get(driver);

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

