package appium;

import org.testng.annotations.Test;
import base.TestBase;

public class NotInstalledAppTests extends TestBase{

	@Test
	public void invalidLoginTest() {
		demoAppObj.clickBoltShirt();
		demoAppObj.validateTitle("Sauce Labs Bolt T-Shirt");
		demoAppObj.clickAdd2Cart();
		demoAppObj.clickCart();
		demoAppObj.clickProceed2Checkout();
		demoAppObj.sendUserName("user");
		demoAppObj.sendPass("wrong pass word");
		demoAppObj.validateErrorMsg("Provided credentials do not match any user in this service.");
	}
}
