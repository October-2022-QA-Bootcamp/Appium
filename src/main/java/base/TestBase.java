package base;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import page_object.CalculatorObject;
import page_object.DemoAppElement;
import page_object.DemoAppObjectApk;
import page_object.DemoAppObject_iOS;
import page_object.HomePage;
import static utils.Constant.*;
import utils.ReadConfig;

public class TestBase {

	protected WebDriver driver;
	ReadConfig appConfig = new ReadConfig(APPLICATION_PROFILE);
	
	protected HomePage homePage;
	protected CalculatorObject calculatorObj;
	protected DemoAppElement demoAppObj;

	@Parameters({"profile"})
	@BeforeMethod
	public void initiateDriver(String profile) {
		if(profile.equalsIgnoreCase(BS_PROFILE)) {
			driver = remoteBrowserStackCapabilities();
		}else if(profile.equalsIgnoreCase(LOCAL_PROFILE)) {
			String appType = appConfig.getProp(APP_TYPE);
			switch (appType) {
			case WEB_APP:
				driver = localAppiumCapabilities();
				driver.get(appConfig.getProp(URL));
				break;
			case INSTALLED_APP:
				driver = localInstalledAppCaps();
				break;
			case NOT_INSTALLED_APP:
				driver = localNotInstalledAppCaps();
				break;
			default:
				driver = localAppiumCapabilities();
				break;
			}
		}
		initPageObjects();
	}

	protected void initPageObjects() {
		ReadConfig config = new ReadConfig(LOCAL_PROFILE);
		homePage = new HomePage(driver);
		calculatorObj = new CalculatorObject(driver);
		if(config.getProp(KEY_PLATFORM).equalsIgnoreCase("android")) {
			demoAppObj = new DemoAppObjectApk(driver);
		}else if(config.getProp(KEY_PLATFORM).equalsIgnoreCase("ios")) {
			demoAppObj = new DemoAppObject_iOS(driver);
		}
		
	}
	
	protected WebDriver localAppiumCapabilities() {
		ReadConfig config = new ReadConfig(LOCAL_PROFILE);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(PLATFORM_NAME, config.getProp(KEY_PLATFORM));
		capabilities.setCapability(PLATFORM_VERSION, config.getProp(KEY_PLATFORM_VERSION));
		capabilities.setBrowserName(config.getProp(KEY_BROWSER));

		URL url = null;
		try {
			url = new URL(config.getProp(KEY_URL));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return new RemoteWebDriver(url, capabilities);
	}

	protected WebDriver localInstalledAppCaps() {
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
		
		return new RemoteWebDriver(url, capabilities);
		
//		if(config.getProp(KEY_PLATFORM).equalsIgnoreCase("android")) {
//			driver = new AndroidDriver(capabilities);
//		}else if(config.getProp(KEY_PLATFORM).equalsIgnoreCase("ios")) {
//			driver = new IOSDriver(capabilities);
//		}else if(config.getProp(KEY_PLATFORM).equalsIgnoreCase("web")) {
//			driver = new ChromeDriver();
//		}
//		return driver;
	}
	
	protected WebDriver localNotInstalledAppCaps() {
		ReadConfig config = new ReadConfig(LOCAL_PROFILE);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(APPIUM+PLATFORM_NAME, config.getProp(KEY_PLATFORM));
		capabilities.setCapability(APPIUM+PLATFORM_VERSION, config.getProp(KEY_PLATFORM_VERSION));
		capabilities.setCapability(APPIUM+APP, new File(config.getProp(KEY_APP_PATH)).getAbsolutePath());

		URL url = null;
		try {
			url = new URL(config.getProp(KEY_URL));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return new RemoteWebDriver(url, capabilities);
	}
	
	protected WebDriver remoteBrowserStackCapabilities() {
		ReadConfig config = new ReadConfig(BS_PROFILE);
		MutableCapabilities capabilities = new MutableCapabilities();
		capabilities.setCapability(BROWSER_NAME, config.getProp(KEY_BROWSER_BS));
		HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
		browserstackOptions.put(PLATFORM_NAME, config.getProp(KEY_PLATFORM_BS));
		browserstackOptions.put(PLATFORM_VERSION, config.getProp(KEY_PLATFORM_VERSION_BS));
		browserstackOptions.put(DEVICE_NAME, config.getProp(KEY_DEVICE_NAME));
		capabilities.setCapability(BS_OPTS, browserstackOptions);

		String urlString = "https://" + config.getProp(KEY_USER_BS) + ":" + config.getProp(KEY_PASS_BS)
				+ config.getProp(KEY_URL_BS);
		
		URL url = null;
		try {
			url = new URL(urlString);
		}catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return new RemoteWebDriver(url, capabilities);
	}
	
	@AfterMethod
	public void terminatingSession() {
		driver.quit();
	}
}
