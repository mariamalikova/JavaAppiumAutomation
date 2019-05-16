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
}
