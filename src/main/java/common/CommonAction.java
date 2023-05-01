package common;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommonAction {

	public static String getText(WebElement element) {
		return element.getText();
	}
	
	public static void insert_n_enter(WebElement element, String text) {
		element.sendKeys(text, Keys.ENTER);
	}
	
	public static void scrollDown(WebDriver driver, int pixel) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript("window.scrollBy(0,"+ pixel +")");
	}
}
