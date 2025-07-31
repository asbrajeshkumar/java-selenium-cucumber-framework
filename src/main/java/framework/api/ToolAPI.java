package framework.api;

import org.openqa.selenium.WebDriver;

public class ToolAPI {

	public static IDriver getDriver(WebDriver pDriver) {
		return new Driver(pDriver);
	}

	public static IElement getElement(IDriver IDriver) {
		return new Element(IDriver);
	}
	
}
