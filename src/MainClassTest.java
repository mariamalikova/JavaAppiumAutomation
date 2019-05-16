import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MainClassTest {

	MainClass mcObject;

	@Before
	public void beforeTests(){
		mcObject = new MainClass();
	}

	@Test
	public void testGetLocalNumber(){
		int expected = 14;
		Assert.assertEquals("The actual and expected values are not equal", expected, mcObject.getLocalNumber());
	}

	@Test
	public void testGetClassNumber(){
		Assert.assertTrue("The actual value less or equal 45",mcObject.getClassNumber()>45);
	}

	@Test
	public void testGetClassString(){
		Assert.assertTrue("The actual string does not contains 'hello' or 'Hello' as substring", mcObject.getClassString().contains("hello") || mcObject.getClassString().contains("Hello"));
	}
}
