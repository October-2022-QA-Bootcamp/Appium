package unittests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class AppiumUnitTests {

	/*
	 * Pre-Req:
	 * 1. Open Appium GUI Server
	 * 2. Start Server
	 * 3. Open emulator
	 * 4. Turn on a device/emulator
	 */
	@Test
	public void webAppTest() throws MalformedURLException {
	    DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
	    desiredCapabilities.setCapability("platformName", "Android");
	    desiredCapabilities.setCapability("appium:platformVersion", "12");
	    desiredCapabilities.setBrowserName("chrome");

	    URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

	    WebDriver driver = new RemoteWebDriver(remoteUrl, desiredCapabilities);
	    
	    driver.get("https://www.amazon.com/");
	}
}
