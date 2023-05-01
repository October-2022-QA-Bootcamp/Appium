package page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static common.CommonAction.*;

public class HomePage {

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "glow-ingress-single-line")
	WebElement locationStatus;
	
	@FindBy(id = "nav-search-keywords")
	WebElement searchItem;
	
	public void locationStatusValidation(String expected) {
		Assert.assertEquals(getText(locationStatus), expected);
	}
	
	public void searchItem(String itemName) {
		insert_n_enter(searchItem, itemName);
	}
}
