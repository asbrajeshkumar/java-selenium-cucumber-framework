package framework.objectRepository;

import org.openqa.selenium.By;

public class orStruct {
	private String locName, locType, locValue;
	int waitInSec = 0;
	public orStruct(String locName, String locType, String locValue, int waitInSec) {
		super();
		this.locName = locName;
		this.locType = locType;
		this.locValue = locValue;
		this.waitInSec = waitInSec;
	}
	
	
	
	public String getLocName() {
		return locName;
	}



	public String getLocType() {
		return locType;
	}



	public String getLocValue() {
		return locValue;
	}



	public int getWaitInSec() {
		return waitInSec;
	}



	public void setLocName(String locName) {
		this.locName = locName;
	}



	public void setLocType(String locType) {
		this.locType = locType;
	}



	public void setLocValue(String locValue) {
		this.locValue = locValue;
	}



	public void setWaitInSec(int waitInSec) {
		this.waitInSec = waitInSec;
	}



	@Override
	public String toString() {
		return "orStruct [locName=" + locName + ", locType=" + locType + ", locValue=" + locValue + ", waitInSec="
				+ waitInSec + "]";
	}
	
	
	public By getBy() {
		String sBy = this.getLocType().toUpperCase();
		By toReturn = null;
		
		switch(sBy) {
		case "ID":
			toReturn = By.id(this.getLocValue());
			break;
		case "XPATH":
			toReturn = By.xpath(this.getLocValue());
			break;
		case "NAME":
			toReturn = By.name(this.getLocValue());
			break;
		case "CLASSNAME":
			toReturn = By.className(this.getLocValue());
			break;
		}
		return toReturn;
	}
    
	public void setParentLocValue(String sParentLoc) {
		setLocValue(sParentLoc);
	}

	public void replaceDynamicObject(String sReplaceValue) throws Exception{
		String sLoc = getLocValue();
		sLoc = sLoc.replace("<REPL>", sReplaceValue);
		setLocValue(sLoc);
	}
    
}

