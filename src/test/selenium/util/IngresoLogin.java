package test.selenium.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class IngresoLogin {
	private WebDriver driver;
	private String url;
	
	public IngresoLogin(WebDriver driver, String url, String usuario, String password) throws Exception {
		this.driver = driver;
		this.url = url;
		ingreso(url);
		login(usuario,password);
		new SeleniumWait(getDriver()).getWait();
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void getWait() {
		 new WebDriverWait(getDriver(), 20);
	}


	/**
	 * 
	 * */
	public void ingreso(String url) throws Exception {
		getDriver().navigate().to(url);
		getWait();
		System.out.println("Ingreso OK URL:" + getDriver().getCurrentUrl());
	}

	public abstract void login(String usuario, String password) throws Exception;
}
