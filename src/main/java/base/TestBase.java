package base;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import page_object.HomePage;
import static utils.Constant.*;
import utils.ReadConfig;

public class TestBase {

	protected WebDriver driver;
	ReadConfig appConfig = new ReadConfig(APPLICATION_PROFILE);
	
	protected HomePage homePage;

	@Parameters({"profile"})
	@BeforeMethod
	public void initiateDriver(String profile) {
		if(profile.equalsIgnoreCase(BS_PROFILE)) {
			driver = remoteBrowserStackCapabilities();
		}else if(profile.equalsIgnoreCase(LOCAL_PROFILE)) {
			driver = localAppiumCapabilities();
		}
		initPageObjects();
		driver.get(appConfig.getProp(URL));
	}

	protected void initPageObjects() {
		homePage = new HomePage(driver);
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