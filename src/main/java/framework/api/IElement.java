package framework.api;

import java.util.List;

import org.openqa.selenium.WebElement;

import framework.objectRepository.orStruct;

public interface IElement {

	public WebElement getWebElement(orStruct oElem)throws Exception;
	
	public List<WebElement> getWebElements(orStruct oElem)throws Exception;
	
	public void click(orStruct oElem)throws Exception;
	
	public void clickOnceClickable(orStruct oElem) throws Exception;
	
	public void setCheckbox(orStruct oElem, Boolean bValue)throws Exception;
	
	public void clearAndSetText(orStruct oElem, String sValue)throws Exception;
	
	public void selectDropdownWithValue(orStruct oElem, String sValue)throws Exception;
	
	public void selectDropdownWithVisibleText(orStruct oElem, String sValue)throws Exception;
	
	public void waitTillElementIsInvisible(orStruct oElem)throws Exception;
	
	public void waitTillElementIsInvisible(orStruct oElem, int WaitInSeconds)throws Exception;
	
	public void waitTillElementIsVisible(orStruct oElem)throws Exception;
	
	public void waitTillElementIsVisible(orStruct oElem, int WaitInSeconds)throws Exception;
	
	public void waitTillElementIsPresent(orStruct oElem)throws Exception;
	
	public void waitTillElementIsPresent(orStruct oElem, int WaitInSeconds)throws Exception;
	
	public void waitTillElementIsClickable(orStruct oElem)throws Exception;
	
	public void waitTillElementIsClickable(orStruct oElem, int WaitInSeconds)throws Exception;
	
	public Boolean isAvailableOnPage(orStruct oElem)throws Exception;
	
	public Boolean isAvailableOnPage(orStruct oElem, int WaitInSeconds)throws Exception;
	
	public Boolean isDisplayed(orStruct oElem)throws Exception;
	
	public Boolean isDisplayed(orStruct oElem, int WaitInSeconds)throws Exception;
	
	public Boolean isEnabled(orStruct oElem)throws Exception;
	
	public Boolean isEnabled(orStruct oElem, int WaitInSeconds)throws Exception;
	
	public String getAttribute(orStruct oElem, String sAttribute)throws Exception;
	
	public String getText(orStruct oElem)throws Exception;
	
	public String getTextFromList(orStruct oElem, int index)throws Exception;
	
	public void checkBoxFromList(orStruct oElem, int index, boolean bCheck) throws Exception;
	
	public void checkAllBoxFromList(orStruct oElem, int index, boolean bCheck) throws Exception;
	
	public void clickFromList(orStruct oElem, int index) throws Exception;
	
	public void sendKeys(orStruct oElem, String sValue)throws Exception;
	
	public void clearAndSendKeys(orStruct oElem, String sValue) throws Exception;
		
	public int getCount(orStruct oElem) throws Exception;

}
