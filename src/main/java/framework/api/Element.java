package framework.api;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.objectRepository.orStruct;
import utilities.LogUtil;

public class Element implements IElement{
	private static final Logger log = LogUtil.getLogger(Element.class);
	IDriver iDriver = null;
	private WebDriver driver = null;

	public Element(IDriver pDriver) {
		iDriver = pDriver;
		driver = iDriver.getDriver();		
	}


	@Override
	public WebElement getWebElement(orStruct oElem) throws Exception{
		WebElement element = null;
		try {
			By byOfOrObj = oElem.getBy();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(oElem.getWaitInSec()));
			wait.until(ExpectedConditions.presenceOfElementLocated(byOfOrObj));
			element = driver.findElement(byOfOrObj);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
		} catch (Exception e) {
			log.fatal("caught unhandled exception while getting webelement.");
		}
		return element;
	}

	@Override
	public List<WebElement> getWebElements(orStruct oElem)throws Exception {
		List<WebElement> elements = new ArrayList<WebElement>();
		try {
			By byOfOrObj = oElem.getBy();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(oElem.getWaitInSec()));
			wait.until(ExpectedConditions.presenceOfElementLocated(byOfOrObj));
			elements = driver.findElements(byOfOrObj);
		} catch (Exception e) {
			log.fatal("caught unhandled exception while getting webelements.");
		}
		return elements;
	}

	@Override
	public void click(orStruct oElem) throws Exception{
		WebElement wElem = null;
		try {
			iDriver.threadSleep(1);
			wElem = getWebElement(oElem);
			wElem.click();
			log.info(oElem.getLocName() +" was clicked.");
		}catch(Exception e) {
			iDriver.threadSleep(1);
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", wElem);
			log.info(oElem.getLocName() +" was clicked with js executor.");
		}

	}

	@Override
	public void clickOnceClickable(orStruct oElem) throws Exception{
		try {
			waitTillElementIsClickable(oElem, oElem.getWaitInSec());
			click(oElem);
		} catch (Exception e) {
			log.fatal("caught unhandled exception while clicking "+oElem.getLocName());
		}
	}

	@Override
	public void setCheckbox(orStruct oElem, Boolean bValue) throws Exception{
		try {
			WebElement element = getWebElement(oElem);
			if(element.isSelected() != bValue) {
				click(oElem);
			}else {
				//do nothing
			}
		} catch (Exception e) {
			log.fatal("caught unhandled exception while setting checkbox for "+oElem.getLocName());
		}
	}

	@Override
	public void clearAndSetText(orStruct oElem, String sValue) throws Exception{
		try {
			iDriver.threadSleep(1);
			WebElement element = getWebElement(oElem);
			element.clear();
			element.sendKeys(sValue);
		} catch (Exception e) {
			log.fatal("caught unhandled exception while entering text for "+oElem.getLocName());
		}
	}

	@Override
	public void selectDropdownWithValue(orStruct oElem, String sValue) throws Exception{
		try{
			Thread.sleep(200);
			WebElement element = getWebElement(oElem);
			Select dropdown = new Select(element);
			dropdown.selectByValue(sValue);
		} catch (Exception e) {
			log.fatal("caught unhandled exception while selecting value from dropdown for "+oElem.getLocName());
		}
	}

	@Override
	public void selectDropdownWithVisibleText(orStruct oElem, String sValue) throws Exception{
		try {
			Thread.sleep(200);
			WebElement element = getWebElement(oElem);
			Select dropdown = new Select(element);
			dropdown.selectByVisibleText(sValue);
		} catch (Exception e) {
			log.fatal("caught unhandled exception while setting checkbox for "+oElem.getLocName());
		}
	}

	@Override
	public void waitTillElementIsInvisible(orStruct oElem) throws Exception{
		waitTillElementIsInvisible(oElem, oElem.getWaitInSec());
	}

	@Override
	public void waitTillElementIsInvisible(orStruct oElem, int WaitInSeconds) throws Exception{
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WaitInSeconds));
			wait.until(ExpectedConditions.invisibilityOfElementLocated(oElem.getBy()));
		} catch (Exception e) {
			log.fatal("Exception thrown for "+oElem.getLocName()+" while waiting to be invisible.");
		}
	}

	@Override
	public void waitTillElementIsVisible(orStruct oElem) throws Exception{
		waitTillElementIsVisible(oElem, oElem.getWaitInSec());
	}

	@Override
	public void waitTillElementIsVisible(orStruct oElem, int WaitInSeconds) throws Exception{
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WaitInSeconds));
			waitTillElementIsPresent(oElem);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", getWebElement(oElem));
			wait.until(ExpectedConditions.visibilityOfElementLocated(oElem.getBy()));
		} catch (Exception e) {
			log.fatal("Exception thrown for "+oElem.getLocName()+" while waiting to be visible.");
		}
	}

	@Override
	public void waitTillElementIsPresent(orStruct oElem) throws Exception{
		waitTillElementIsPresent(oElem, oElem.getWaitInSec());
	}

	@Override
	public void waitTillElementIsPresent(orStruct oElem, int WaitInSeconds) throws Exception{
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WaitInSeconds));
			wait.until(ExpectedConditions.presenceOfElementLocated(oElem.getBy()));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", getWebElement(oElem));
		} catch (Exception e) {
			log.fatal("Exception thrown for "+oElem.getLocName()+" while waiting to be present.");
		}
	}

	@Override
	public void waitTillElementIsClickable(orStruct oElem) throws Exception{
		waitTillElementIsClickable(oElem, oElem.getWaitInSec());
	}

	@Override
	public void waitTillElementIsClickable(orStruct oElem, int WaitInSeconds)throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WaitInSeconds));
			wait.until(ExpectedConditions.elementToBeClickable(oElem.getBy()));
		} catch (Exception e) {
			log.fatal("Exception thrown for "+oElem.getLocName()+" while waiting to be clickable.");
		}
	}


	@Override
	public Boolean isAvailableOnPage(orStruct oElem)throws Exception {
		WebElement element = null;
		try {
			waitTillElementIsPresent(oElem, oElem.getWaitInSec());
			element = getWebElement(oElem);
			if(element !=null) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			log.fatal("Exception thrown for "+oElem.getLocName()+" while checking presence on page.");
			return false;
		}
	}

	@Override
	public Boolean isAvailableOnPage(orStruct oElem, int WaitInSeconds)throws Exception {
		WebElement element = null;
		try {
			waitTillElementIsPresent(oElem, WaitInSeconds);
			element = getWebElement(oElem);
			if(element !=null) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			log.fatal("Exception thrown for "+oElem.getLocName()+" while checking presence on page.");
			return false;
		}
	}

	@Override
	public Boolean isDisplayed(orStruct oElem)throws Exception {
		WebElement element = null;
		try {
			waitTillElementIsVisible(oElem, oElem.getWaitInSec());
			element = getWebElement(oElem);
			if(element !=null) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			log.fatal("Exception thrown for "+oElem.getLocName()+" while checking displayed on page.");
			return false;
		}
	}

	@Override
	public Boolean isDisplayed(orStruct oElem, int WaitInSeconds)throws Exception {
		WebElement element = null;
		try {
			waitTillElementIsVisible(oElem, WaitInSeconds);
			element = getWebElement(oElem);
			if(element !=null) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			log.fatal("Exception thrown for "+oElem.getLocName()+" while checking displayed on page.");
			return false;
		}
	}

	@Override
	public Boolean isEnabled(orStruct oElem)throws Exception {
		WebElement element = null;
		try {
			waitTillElementIsPresent(oElem, oElem.getWaitInSec());
			element = getWebElement(oElem);
			if(element.isEnabled()) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			log.fatal("Exception thrown for "+oElem.getLocName()+" while checking if enabled.");
			return false;
		}
	}

	@Override
	public Boolean isEnabled(orStruct oElem, int WaitInSeconds)throws Exception {
		WebElement element = null;
		try {
			waitTillElementIsPresent(oElem, WaitInSeconds);
			element = getWebElement(oElem);
			if(element.isEnabled()) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			log.fatal("Exception thrown for "+oElem.getLocName()+" while checking if enabled.");
			return false;
		}
	}


	@Override
	public String getAttribute(orStruct oElem, String sAttribute)throws Exception {
		String sAttributeValue = null;
		WebElement element = null;
		try {
			waitTillElementIsPresent(oElem, oElem.getWaitInSec());
			element = getWebElement(oElem);
			sAttributeValue = element.getDomAttribute(sAttribute);
		}catch(Exception e) {
			log.fatal("Exception thrown for while getting attribute["+sAttribute+"] for "+oElem.getLocName());
			return null;
		}
		return sAttributeValue;
	}

	@Override
	public String getText(orStruct oElem)throws Exception {
		WebElement element = null;
		String sText = null;
		try {
			waitTillElementIsPresent(oElem, oElem.getWaitInSec());
			element = getWebElement(oElem);
			sText = element.getText();
		}catch(Exception e) {
			log.fatal("Exception thrown for while getting text value of "+oElem.getLocName());
			return null;
		}
		return sText;
	}

	@Override
	public String getTextFromList(orStruct oElem, int index) throws Exception{
		String sText = null;
		List<WebElement> element = null;
		try {
			waitTillElementIsPresent(oElem, oElem.getWaitInSec());
			element = getWebElements(oElem);
			Thread.sleep(200);
			sText = element.get(index).getText();
		}catch(Exception e) {
			log.fatal("Exception thrown for while getting text value of "+oElem.getLocName());
			return null;
		}
		return sText;
	}

	@Override
	public void checkBoxFromList(orStruct oElem, int index, boolean bCheck) throws Exception{
		List<WebElement> elements = null;
		WebElement element = null;
		try {
			waitTillElementIsPresent(oElem, oElem.getWaitInSec());
			elements = getWebElements(oElem);
			element = elements.get(index);
			Thread.sleep(200);
			if(!element.isSelected() && bCheck) {
				try {
					element.click();
					log.info(oElem.getLocName()+" clicked");
				}catch(Exception e) {
					JavascriptExecutor executor = (JavascriptExecutor)driver;
					executor.executeScript("arguments[0].click();", element);
					log.info(oElem.getLocName()+" clicked using js executor");
				}
			}
		}catch(Exception e) {
		}
	}

	@Override
	public void checkAllBoxFromList(orStruct oElem, int index, boolean bCheck) throws Exception{
		List<WebElement> elements = null;
		WebElement element = null;
		try {
			waitTillElementIsPresent(oElem, oElem.getWaitInSec());
			elements = getWebElements(oElem);
			for(int i=0; i<index; i++) {
				element = elements.get(i);
				Thread.sleep(200);
				if(!element.isSelected() && bCheck) {
					try {
						element.click();
						log.info(oElem.getLocName()+" clicked");
					}catch(Exception e) {
						JavascriptExecutor executor = (JavascriptExecutor)driver;
						executor.executeScript("arguments[0].click();", element);
						log.info(oElem.getLocName()+" clicked using js executor");
					}
				}
			}
		}catch(Exception e) {
		}
	}

	@Override
	public void clickFromList(orStruct oElem, int index) throws Exception{
		List<WebElement> elements = null;
		WebElement element = null;
		try {
			waitTillElementIsPresent(oElem, oElem.getWaitInSec());
			elements = getWebElements(oElem);
			element = elements.get(index);
			Thread.sleep(200);
			try {
				element.click();
				log.info(oElem.getLocName()+" clicked");
			}catch(Exception e) {
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click();", element);
				log.info(oElem.getLocName()+" clicked using js executor");
			}
		}catch(Exception e) {
		}
	}

	@Override
	public void sendKeys(orStruct oElem, String sValue) throws Exception{
		WebElement element = null;
		try {
			waitTillElementIsPresent(oElem, oElem.getWaitInSec());
			Thread.sleep(1000);
			element = getWebElement(oElem);
			click(oElem);
			element.clear();
			element.sendKeys(sValue);
		} catch (Exception e) {
			log.fatal("Caught exception while sending keys for "+oElem.getLocName());
		}
	}

	@Override
	public void clearAndSendKeys(orStruct oElem, String sValue) throws Exception{
		WebElement element = null;
		try {
			waitTillElementIsPresent(oElem, oElem.getWaitInSec());
			element = getWebElement(oElem);
			if (element.isDisplayed() && element.isEnabled()) {
				element.clear(); 
				element.sendKeys("\u0001");
				element.sendKeys("\u0008");
				try {
					if (!element.getDomAttribute("value").isEmpty()) {
						((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", element);
					}
				} catch (Exception e) {
					//Nothing to log
				}
				element.sendKeys(sValue);
			} 
		}catch (Exception e) {
			log.fatal("Caught exception while sending keys for "+oElem.getLocName());
		}
	}

	@Override
	public int getCount(orStruct oElem) throws Exception{
		int iCount = 0;
		iCount = getWebElements(oElem).size();
		return iCount;
	}


}
