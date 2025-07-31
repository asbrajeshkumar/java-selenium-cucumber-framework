package framework.driver;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import framework.api.IDriver;
import framework.api.ToolAPI;
import utilities.ConfigManager;
import utilities.Constants;
import utilities.LogUtil;

public class DriverManager {
	private static final Logger log = LogUtil.getLogger(DriverManager.class);
	private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

	public static void setDriver(WebDriver driver) {
		driverThread.set(driver);
	}

	public static WebDriver getDriver() {
		return driverThread.get();
	}
	
	public static void quitDriver() {
        if (driverThread.get() != null) {
            driverThread.get().quit();
            driverThread.remove();
        }
    }

	public static IDriver preparePrerequisites() {
		log.info("Inside preparePrerequisites");
		String BROWSER = ConfigManager.getProps().getProperty("TARGET.BROWSER");
		String sURL = ConfigManager.getProps().getProperty(Constants.AUT+".URL");
		IDriver iDriver;
		iDriver = getDriver(BROWSER);
		try {
			iDriver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
			iDriver.getDriver().get(sURL);
		} catch (Exception e) {
			log.fatal("Caught exception in preparePrerequisites");
			e.printStackTrace();
		}

		return iDriver;
	}

	private static IDriver getDriver(String BROWSER) {
		WebDriver driver = null;
		IDriver iDriver = null;
		log.info("Inside getDriver");
		try {
			driver = BrowserFactory.getBrowserInstance(BROWSER);
			iDriver = ToolAPI.getDriver(driver);
		} catch (Exception e) {
			log.fatal("Caught exception in getDriver");
			e.printStackTrace();
		}
		return iDriver;
	}


}
