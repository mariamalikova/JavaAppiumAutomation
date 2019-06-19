import lib.CoreTestCase;
import lib.UI.ArticlePageObject;
import lib.UI.BookmarkListPageObject;
import lib.UI.MainPageObject;
import lib.UI.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FirstTests extends CoreTestCase {

	private MainPageObject mainPAgeObject;

	protected void setUp() throws Exception{
		super.setUp();
		mainPAgeObject = new MainPageObject(driver);
	}

	// Ex 2
	@Test
	public void testCheckSearchField(){

		SearchPageObject searchPageObject = new SearchPageObject(driver);

		searchPageObject.initSearchInput();
		searchPageObject.typeSearchLine("Java");
		searchPageObject.waitForSearchResult("Object-oriented programming language");
	}

	// Ex 3
	@Test
	public void testCheckSearchResultPresent(){

		SearchPageObject searchPageObject = new SearchPageObject(driver);

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

		SearchPageObject searchPageObject = new SearchPageObject(driver);
		String searchString = "java";

		searchPageObject.initSearchInput();
		searchPageObject.typeSearchLine(searchString);

		List<WebElement> search_results = searchPageObject.getSearchResult();

		for (WebElement we : search_results){
			Assert.assertTrue("Result does not contains search world", we.getText().toLowerCase().contains(searchString));
		}
	}

	// Ex 5
	@Test
	public void testSaveTwoArticlesToMyList() {

		String folderName = "Learning programming";
		String firstArticleTitle = "Java";
		String firstArticleSubtitle = "Object-oriented programming language";
		String SecondArticleTitle = "Kotlin";

		SearchPageObject searchPageObject = new SearchPageObject(driver);
		ArticlePageObject articlePageObject = new ArticlePageObject(driver);
		BookmarkListPageObject bookmarkPageObject = new BookmarkListPageObject(driver);

		searchPageObject.initSearchInput();
		searchPageObject.typeSearchLine(firstArticleTitle);
		searchPageObject.clickForSearchResult("Object-oriented programming language");

		articlePageObject.addArticleToBookmarksAndCreateList(folderName);
		articlePageObject.returnToMainScreen();

		searchPageObject.initSearchInput();
		searchPageObject.typeSearchLine(SecondArticleTitle);
		searchPageObject.clickForSearchResult("programming language");

		articlePageObject.addArticleToBookmarks(folderName);
		articlePageObject.returnToMainScreen();

		mainPAgeObject.openBookmarksList(folderName);

		bookmarkPageObject.deleteArticleFromListBySwipe(SecondArticleTitle);
		bookmarkPageObject.openArticle(firstArticleTitle);
		String articleSubtitle = articlePageObject.getArticleSubtitle();
		Assert.assertEquals("Title does not match. Expected: " + firstArticleSubtitle + ", actual: " + articleSubtitle, firstArticleSubtitle, articleSubtitle);
	}

	// Ex 6
	@Test
	public void testCheckArticleTitleWithoutWaiting(){

		SearchPageObject searchPageObject = new SearchPageObject(driver);
		ArticlePageObject articlePageObject = new ArticlePageObject(driver);
		String search_str = "java";

		searchPageObject.initSearchInput();
		searchPageObject.typeSearchLine(search_str);
		searchPageObject.clickForSearchResult("programming language");
		articlePageObject.assertSubtitlePresent();
	}

	// Ex 7
	@Test
	public void testChangeScreenOrientationOnSearchResults() {

		SearchPageObject searchPageObject = new SearchPageObject(driver);
		ArticlePageObject articlePageObject = new ArticlePageObject(driver);

		searchPageObject.initSearchInput();
		searchPageObject.typeSearchLine("Java");
		searchPageObject.clickForSearchResult("Object-oriented programming language");

		String articleSubtitleBeforeRotation = articlePageObject.getArticleSubtitle();

		this.rotateScreenLandscape();

		String articleSubtitleAfterRotation = articlePageObject.getArticleSubtitle();

		Assert.assertEquals("Article title have been changed after rotation", articleSubtitleAfterRotation, articleSubtitleBeforeRotation);
	}


}

