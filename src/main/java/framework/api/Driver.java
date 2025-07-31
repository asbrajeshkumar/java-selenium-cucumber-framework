package framework.api;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import framework.extentReport.TestReporter;
import utilities.LogUtil;

public class Driver implements IDriver {

	private static final Logger log = LogUtil.getLogger(Driver.class);
	private WebDriver driver = null;
	SoftAssert sAssert = new SoftAssert();
	public Driver(WebDriver pDriver) {
		driver = pDriver;
	}

	@Override
	public WebDriver getDriver() {
		return driver;
	}

	@Override
	public WebElement findElement(By byOption) {
		return driver.findElement(byOption);
	}

	@Override
	public List<WebElement> findElements(By byOption){
		return driver.findElements(byOption);
	}


	@Override
	public void navigateTo(String sURL){
		driver.get(sURL);
	}

	@Override
	public void closeDriver(){
		log.info("Closing driver.");
		threadSleep(1);
		driver.close();
	}

	@Override
	public void quitDriver(){
		driver.quit();
	}

	@Override
	public void threadSleep(int iSec) {
		try {
			Thread.sleep(iSec*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void waitForPageLoad() {
		ExpectedCondition<Boolean>pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver pDriver) {
				return((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(pageLoadCondition);
	}

	@Override
	public void captureScreenshot(String fileWithPath) throws IOException {
		log.info("Capturing screenshot.");
		try {
			TakesScreenshot scrShot = ((TakesScreenshot)driver);
			File ScrFile = scrShot.getScreenshotAs(OutputType.FILE);
			File DestFile = new File(fileWithPath+"");
			FileUtils.copyFile(ScrFile, DestFile);
		} catch (WebDriverException e) {
			log.fatal("Caught WebDriverException exception while capturing screenshot");
			e.printStackTrace();
		} catch (IOException e) {
			log.fatal("Caught WebDriverException IOException while capturing screenshot");
			e.printStackTrace();
		}
	}
	
	@Override
	public void pass(String message) {
		TestReporter.pass(message);
    }

	@Override
    public void fail(String message) {
    	TestReporter.fail(message);
    }

	@Override
    public void info(String message) {
    	TestReporter.info(message);
    }

	@Override
    public void skip(String message) {
    	TestReporter.skip(message);
    }
	
	
	@Override
	public void switchWindow() {
		String parentWindow = driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		Iterator<String> iterator = windowHandles.iterator();
		while (iterator.hasNext()) {
			String childWindow = iterator.next();
			if (!childWindow.equals(parentWindow)) {
				driver.switchTo().window(childWindow);
			}
		}
	}

	@Override
	public void CloseAndSwitchWindow() {
		String parentWindow = driver.getWindowHandle();
		closeDriver();
		Set<String> windowHandles = driver.getWindowHandles();
		Iterator<String> iterator = windowHandles.iterator();
		while (iterator.hasNext()) {
			String childWindow = iterator.next();
			if (!childWindow.equals(parentWindow)) {
				driver.switchTo().window(childWindow);
			}
		}
	}

	@Override
	public int getSessionCount() {
		return driver.getWindowHandles().size();
	}

	@Override
	public String getURL() {
		return driver.getCurrentUrl();
	}

	@Override
	public void scrollToBottom() {
		try {
			threadSleep(5);
			Actions actions = new Actions(driver);
			actions.sendKeys(org.openqa.selenium.Keys.END).perform();
		} catch (Exception e) {
			log.fatal("caught unhandled exception while page scroll.");
		}
	}

}

