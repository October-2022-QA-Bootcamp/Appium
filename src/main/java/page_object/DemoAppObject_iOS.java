package page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import static common.CommonAction.*;

import java.time.Duration;

public class DemoAppObject_iOS implements DemoAppElement{

	WebDriver driver;
	WebDriverWait wait;
	
	public DemoAppObject_iOS(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}
	
	@FindBy(xpath = "(//android.view.ViewGroup[@content-desc=\"store item\"])[3]/android.view.ViewGroup[1]/android.widget.ImageView")
	WebElement boltT_Shirt;
	
	@FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"container header\"]/android.widget.TextView")
	WebElement titleOfShirt;
	
	@FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Add To Cart button\"]")
	WebElement add2Cart;
	
	@FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"cart badge\"]/android.widget.ImageView")
	WebElement cart;
	
	@FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Proceed To Checkout button\"]/android.widget.TextView")
	WebElement proceed2Checkout;
	
	@FindBy(xpath = "//android.widget.EditText[@content-desc=\"Username input field\"]")
	WebElement userName;
	
	@FindBy(xpath = "//android.widget.EditText[@content-desc=\"Password input field\"]")
	WebElement password;
	
	@FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"Login button\"]")
	WebElement loginBtn;
	
	@FindBy(xpath = "//android.view.ViewGroup[@content-desc=\"generic-error-message\"]/android.widget.TextView")
	WebElement errorMsg;
	
	public void clickBoltShirt() {
		click(boltT_Shirt);
	}
	
	public void validateTitle(String expected) {
		Assert.assertEquals(getText(titleOfShirt), expected);
	}
	
	public void clickAdd2Cart() {
		click(add2Cart);
	}
	
	public void clickCart() {
		click(cart);
	}
	
	public void clickProceed2Checkout() {
		click(proceed2Checkout);
	}
	
	public void sendUserName(String user) {
		//wait.until(ExpectedConditions.elementToBeClickable(userName));
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
		}
		insert(userName, user);
	}
	
	public void sendPass(String pass) {
		insert(password, pass);
	}
	
	public void clickLogin() {
		click(loginBtn);
	}
	
	public void validateErrorMsg(String expected) {
		Assert.assertEquals(getText(errorMsg), expected);
	}
	
}
