package pages;

import framework.BasePage;
import framework.api.IDriver;
import framework.objectRepository.Structure;
import framework.objectRepository.orStruct;

public class LoginPage extends BasePage{

	@Structure(name = "username")
    public orStruct usernameField;

    @Structure(name = "password")
    public orStruct passwordField;

    @Structure(name = "submit")
    public orStruct submitButton;

    public LoginPage(IDriver iDriver) {
    	super(iDriver);
		super.initOR(this);
    }

    public void login(String user, String pass) {
    	
    	
    }
}
