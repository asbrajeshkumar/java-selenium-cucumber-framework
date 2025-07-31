package framework.api;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface IDriver {

	public WebDriver getDriver();
	
	public WebElement findElement(By byOption);
	
	public List<WebElement> findElements(By byOption);
	
	public void navigateTo(String sURL);
	
	public void closeDriver();
	
	public void quitDriver();
	
	public void threadSleep(int iSec);
	
	public void waitForPageLoad();
	
	public void captureScreenshot(String fileWithPath) throws IOException;
	
	public void switchWindow();
	
	public void CloseAndSwitchWindow();
	
	public int getSessionCount();

	public String getURL();
	
	public void scrollToBottom();

	void skip(String message);

	void fail(String message);

	void info(String message);

	void pass(String message);



	
	
	
	
	
}
