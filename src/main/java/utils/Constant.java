package utils;

public interface Constant {

	String LOCAL_PROFILE = "local";
	String BS_PROFILE = "bs";
	String APPLICATION_PROFILE = "application";
	
	//------ Keys of application properties ------
	String URL = "url";

	// ----- Desire Capabilities -----
	String PLATFORM_NAME = "platformName";
	String PLATFORM_VERSION = "platformVersion";
	String BROWSER_NAME = "browserName";
	String DEVICE_NAME = "deviceName";
	String BS_OPTS = "bstack:options";

	// ----- Keys of local properties -----
	String KEY_PLATFORM = "platform";
	String KEY_PLATFORM_VERSION = "platformVersion";
	String KEY_BROWSER = "browser";
	String KEY_URL = "url";

	//----- Keys of bs properties -----
	String KEY_PLATFORM_BS = "os";
	String KEY_PLATFORM_VERSION_BS = "osVersion";
	String KEY_DEVICE_NAME = "deviceName";
	String KEY_BROWSER_BS = "browserName";
	String KEY_URL_BS = "bsUrl";
	String KEY_USER_BS = "bsUser";
	String KEY_PASS_BS = "bsPass";
}
