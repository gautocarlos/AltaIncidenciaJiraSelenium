package test.selenium.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IngresoLoginCas extends IngresoLogin {

	public IngresoLoginCas(WebDriver driver, String url, String usuario, String password) throws Exception {
		super(driver, url, usuario, password);
	}

	@Override
	public void login(String usuario, String password) throws Exception {
		WebDriver driver = getDriver();
		driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div/div/div/div[2]/div/input")).clear();
		driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div/div/div/div[2]/div/input")).sendKeys(usuario);
		driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div/div/div/div[3]/div/input")).clear();
		driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div/div/div/div[3]/div/input"))
				.sendKeys(password);
		driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div/div/div/div[4]/button")).click();
		new SeleniumWait(driver).getWait();
	}

}
