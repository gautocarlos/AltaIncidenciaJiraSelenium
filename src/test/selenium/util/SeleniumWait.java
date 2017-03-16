package test.selenium.util;

import static org.junit.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumWait {

	private WebDriver driver;

	public SeleniumWait(WebDriver driver) {
		this.driver = driver;
	}

	private WebDriver getDriver() {
		return driver;
	}

	public void getWait() {
		try {
			System.out.println("Inicia espera.");
			Thread.sleep(3000);
			System.out.println("Finaliza espera.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void waitElementByXpath(String xpath) {
		for (int second = 0;; second++) {
			if (second >= 60)
				fail("timeout");
			try {
				if (isElementPresent(By.xpath(xpath))) {
					System.out.println("Existe el elemento con xpath: " + xpath);
					break;
				}
				Thread.sleep(1000);
			} catch (Exception e) {
				System.out.println("NO existe el elemento con xpath: " + xpath);
				System.out.println(e.getMessage());
			}

		}

	}

	private boolean isElementPresent(By by) {
		try {
			getDriver().findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void waitElementById(String id) {
		for (int second = 0;; second++) {
			if (second >= 60)
				fail("timeout");
			try {
				if (isElementPresent(By.id(id))) {
					System.out.println("Existe el elemento con id: " + id);
					break;
				}
				Thread.sleep(1000);
			} catch (Exception e) {
				System.out.println("NO existe el elemento con id: " + id);
				System.out.println(e.getMessage());
			}

		}

	}

}
