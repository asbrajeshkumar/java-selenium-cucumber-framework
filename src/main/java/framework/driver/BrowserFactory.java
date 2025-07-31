package framework.driver;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.LogUtil;

public class BrowserFactory {
	
	private static final Logger log = LogUtil.getLogger(BrowserFactory.class);
	
	public static WebDriver getBrowserInstance(String BROWSER) {
		return setBrowserInstance(BROWSER);
	}
	
	private static WebDriver setBrowserInstance(String BROWSER) {
		log.info("Inside getConfiguredBrowserInstance");
		WebDriver driver = null;
		switch(BROWSER.toUpperCase()) {
		case "CHROME":
			driver = getChromeDriver();
			break;
		case "EDGE":
			driver = getEdgeDriver();
			break;
		default:
			driver = getChromeDriver();
			break;
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		return driver;
	}

	private static WebDriver getChromeDriver() {
		WebDriver driver = null;
		log.info("Inside getChromeDriver");
		try {
			Thread.sleep(1000);
			ChromeOptions options = new ChromeOptions();
			options.setCapability("acceptInsecureCerts", true);
			options.addArguments("--disable-extensions");
			options.addArguments("--remote-allow-origins=*");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
		}catch(Exception e) {
			log.fatal("Caught exception in getChromeDriver");
		}
		return driver;
	}
	
	private static WebDriver getEdgeDriver() {
		WebDriver driver = null;
		log.info("Inside getEdgeDriver");
		try {
			Thread.sleep(1000);
			EdgeOptions options = new EdgeOptions();
			options.setCapability("acceptInsecureCerts", true);
			options.addArguments("--disable-extensions");
			options.addArguments("--remote-allow-origins=*");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver(options);
		}catch(Exception e) {
			log.fatal("Caught exception in getEdgeDriver");
		}
		return driver;
	}
}
