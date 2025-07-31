package utilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

	public static Properties props;

	private String app;
	private String url;
	private String loginUser;
	private String loginPassword;
	
	public ConfigManager() {
		getCredential();		
	}
	
	private void getCredential() {
		this.app = getTargetApp();
		this.url = getTargetURL();
		this.loginUser = getLoginID();
		this.loginPassword = getLoginPwd();
	}
	
	private static String getTargetApp() {
		String appUnderTest = props.getProperty("TARGET.APP");
		Constants.AUT = appUnderTest;
		return appUnderTest;
	}
	
	private static String getTargetURL() {
		String app = getTargetApp();
		return props.getProperty(app+".URL");
	}
	
	private static String getLoginID() {
		String app = getTargetApp();
		return props.getProperty(app+".USERNAME");
	}
	
	private static String getLoginPwd() {
		String app = props.getProperty("TARGET.APP");
		return props.getProperty(app+".PASSWORD");
	}
	
	static void loadProperties() {
		if(null==props) {
			try {
				FileReader reader = new FileReader(Constants.RUN_CONFIG);
				props = new Properties();
				props.load(reader);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	static {
		loadProperties();
	}

	public static Properties getProps() {
		return props;
	}

	public static void setProps(Properties props) {
		ConfigManager.props = props;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
		
	public static String get(String key) {
        return props.getProperty(key);
    }

    public static int getInt(String key) {
        return Integer.parseInt(props.getProperty(key));
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(props.getProperty(key));
    }


}
