package test.selenium.util;

import org.openqa.selenium.WebDriver;

public abstract class DriverWeb {
	private WebDriver driver;
		
	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public abstract WebDriver inicializar() throws Exception;
}
