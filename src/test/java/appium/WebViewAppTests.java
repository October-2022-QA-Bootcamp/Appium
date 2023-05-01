package appium;

import org.testng.annotations.Test;
import base.TestBase;
import common.CommonAction;

public class WebViewAppTests extends TestBase{

	@Test
	public void searchItemTest() {
		homePage.locationStatusValidation("Select a location to see product availability");
		homePage.searchItem("iphone 14");
		CommonAction.scrollDown(driver, 1000);
	}
}
