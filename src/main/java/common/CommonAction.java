package common;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.Logs;

public class CommonAction {

	public static String getText(WebElement element) {
		Logs.log(element + " has text : " + element.getText());
		return element.getText();
	}
	
	public static void insert(WebElement element, String text) {
		element.sendKeys(text);
		Logs.log(text + " >>> has been inserted into >>> " + element);
	}
	
	public static void insert_n_enter(WebElement element, String text) {
		element.sendKeys(text, Keys.ENTER);
		Logs.log(text + " >>> has been inserted into >>> " + element);
	}
	
	public static void click(WebElement element) {
		Logs.log(element + " has been clicked");
		element.click();
	}
	
	public static void scrollDown(WebDriver driver, int pixel) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript("window.scrollBy(0,"+ pixel +")");
		Logs.log("Scroll down by " + pixel + " pixel");
	}
}
