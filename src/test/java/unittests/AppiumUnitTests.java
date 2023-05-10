package unittests;

import org.testng.annotations.Test;
import utils.ReadConfig;
import static utils.Constant.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AppiumUnitTests {

	/*
	 * Pre-Req to inspect and running local devices/emulators/simulators:
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
	
	@Test
	public void browserStackDevice() throws MalformedURLException, InterruptedException {
		MutableCapabilities capabilities = new MutableCapabilities();
		capabilities.setCapability("browserName", "chrome");
		HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
		browserstackOptions.put("platformName", "android");
		browserstackOptions.put("platformVersion", "9.0");
		browserstackOptions.put("deviceName", "Google Pixel 3");
		capabilities.setCapability("bstack:options", browserstackOptions);

		String urlString = "https://" + "" + ":" + ""
				+ "@hub-cloud.browserstack.com/wd/hub";
		
		URL url = new URL(urlString);
	    
	    WebDriver driver = new RemoteWebDriver(url, capabilities);
	    
	    driver.get("https://www.amazon.com/");
	    
	    WebElement element = driver.findElement(By.id("glow-ingress-single-line"));
	    System.out.println(element.getText());
	    
	    element = driver.findElement(By.id("nav-search-keywords"));
	    element.sendKeys("iphone 14", Keys.ENTER);
	    Thread.sleep(3000);
	    
	    JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
	    //jsExecutor.executeScript("window.scrollBy(0,-250)"); //down
	    //Thread.sleep(1500);
	    jsExecutor.executeScript("window.scrollBy(0,500)"); //up
	    
	    driver.quit();
	}
	
	@Test
	public void installedAppTest() {
		ReadConfig config = new ReadConfig(LOCAL_PROFILE);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(APPIUM+PLATFORM_NAME, config.getProp(KEY_PLATFORM));
		capabilities.setCapability(APPIUM+PLATFORM_VERSION, config.getProp(KEY_PLATFORM_VERSION));
		capabilities.setCapability(APPIUM+APP_ACTIVITY, config.getProp(KEY_APP_ACTIVITY));
		capabilities.setCapability(APPIUM+APP_PACKAGE, config.getProp(KEY_APP_PACKAGE));

		URL url = null;
		try {
			url = new URL(config.getProp(KEY_URL));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		WebDriver driver = new RemoteWebDriver(url,capabilities);
		driver.quit();
	}
	
	@Test
	public void startServer() {
		//AppiumServiceBuilder builder = new AppiumServiceBuilder();
		String i = "-88";
		System.out.println(i.replaceAll("[0-9]", ""));
	}
}
