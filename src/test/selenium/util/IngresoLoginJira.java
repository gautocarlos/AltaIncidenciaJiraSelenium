package test.selenium.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IngresoLoginJira extends IngresoLogin {

	public IngresoLoginJira(WebDriver driver, String url, String usuario, String password) throws Exception {
		super(driver, url, usuario, password);
	}

	@Override
	public void login(String usuario, String password) throws Exception {
		try {
			WebDriver driver = getDriver();
			SeleniumWait espera = new SeleniumWait(driver);
			espera.waitElementById("login-form-username");
			driver.findElement(By.id("login-form-username")).clear();
			driver.findElement(By.id("login-form-username")).sendKeys(usuario);
			driver.findElement(By.id("login-form-password")).clear();
			driver.findElement(By.id("login-form-password")).sendKeys(password);
			driver.findElement(By.id("login-form-submit")).click();
			new SeleniumWait(driver).getWait();
			System.out.println("Login JIRA OK");
		}
		catch (Exception e) {
			System.out.println("Login JIRA NO OK");
			System.out.println(e.getMessage());
			throw e;
		}

	}

}
