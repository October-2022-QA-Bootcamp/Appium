package utils;

import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

	Properties properties;

	public ReadConfig(String profile) {
		String file = null;
		if (profile.equalsIgnoreCase("bs")) {
			file = "browser_stack.properties";
		} else if (profile.equalsIgnoreCase("local")) {
			file = "local_caps.properties";
		}else if(profile.equalsIgnoreCase("application")) {
			file = "application.properties";
		}
		loadProperties(file);
	}

	private void loadProperties(String file) {
		properties = new Properties();
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getProp(String key) {
		return properties.getProperty(key);
	}
}
