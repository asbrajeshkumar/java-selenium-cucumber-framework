package hooks;

import framework.BasePage;
import framework.api.IDriver;
import framework.driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import utilities.ConfigManager;

public class Hooks extends BasePage{

	
    @Before
    public void setUp() {
    	new ConfigManager();
		IDriver iDriver = DriverManager.preparePrerequisites();
		DriverManager.setDriver(iDriver.getDriver());
    }

    
    
    @After
    public void tearDown() {
    	DriverManager.quitDriver();
    }
}
