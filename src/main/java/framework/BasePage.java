package framework;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import framework.api.IDriver;
import framework.api.IElement;
import framework.api.ToolAPI;
import framework.objectRepository.OrLoader;
import framework.objectRepository.orStruct;
import framework.objectRepository.structureInjector;
import utilities.Constants;
import utilities.LogUtil;

public class BasePage {
	private static final Logger log = LogUtil.getLogger(BasePage.class);
	protected WebDriver driver = null;
	protected IDriver idriver = null;
	protected IElement elem = null;
	
	protected HashMap<String, orStruct> orMap = null;
	
	public BasePage() {
		orMap = new HashMap<String, orStruct>();
	}
	
	public BasePage(IDriver IDriver) {
		idriver = IDriver;
		driver = IDriver.getDriver();
		elem = ToolAPI.getElement(idriver);
		orMap = new HashMap<String, orStruct>();
	}
	
	protected void initOR(BasePage page) {
		log.info("Initiated object repository for ["+page.getClass().getSimpleName()+"]");
		String aPath = Constants.BASEPATH+"\\src\\test\\resources\\locators\\"+page.getClass().getSimpleName()+".json";
		OrLoader loader = new OrLoader(aPath);
		structureInjector.inject(this, loader);
	}
}
