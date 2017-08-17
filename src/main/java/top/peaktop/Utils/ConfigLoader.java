package top.peaktop.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
	private static final String CONFIG_PROPERTIES="ossConfig.properties";
	public static String loadConfigResource(String key)throws IOException{
		ClassLoader loader =Thread.currentThread().getContextClassLoader();
		Properties properties = new Properties();
		InputStream in = loader.getResourceAsStream(CONFIG_PROPERTIES);
		properties.load(in);
		String value =properties.getProperty(key);
		in.close();
		return value;
	}
}
