package test.selenium.frontend.jira;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import test.selenium.util.IngresoLoginJira;
import test.selenium.util.SeleniumWait;

public class AltaIncidenciaJiraVisual {
	private WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	private StringBuffer verificationErrors = new StringBuffer();
	WebDriverWait wait;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:\\WebDrivers\\geckodriver.exe");
		// Fix problema certificado
		ProfilesIni allProfiles = new ProfilesIni();
		FirefoxProfile profile = allProfiles.getProfile("default");
		profile.setAcceptUntrustedCertificates(true);
		profile.setAssumeUntrustedCertificateIssuer(false);
		// Fix problema certificado
		setDriver(new FirefoxDriver(profile));
		getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void ingreso(String url) {
		System.out.println("URL: " + url);
		driver.navigate().to(url);
		wait = new WebDriverWait(driver, 10);
	}

	public void datosLogin(String usuario, String password) {
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
		} catch (Exception e) {
			System.out.println("Login JIRA NO OK");
			System.out.println(e.getMessage());
			throw e;
		}
	}

	public void altaIncidenciaJira() throws Exception {
		SeleniumWait espera = new SeleniumWait(driver);
		espera.waitElementById("create_link");
		driver.findElement(By.id("create_link")).click();
		driver.findElement(By.id("issuetype-field")).clear();
		driver.findElement(By.id("issuetype-field")).sendKeys("COM_Bug");
		driver.findElement(By.linkText("COM_Bug")).click();
		espera.getWait();
//		espera.waitElementById("assignee-field");
		driver.findElement(By.id("summary")).sendKeys("Ejemplo alta de Jira automatizado");
		driver.findElement(By.id("customfield_10197")).click();
		driver.findElement(By.id("customfield_10197")).sendKeys("P");
		new Select(driver.findElement(By.id("customfield_10197"))).selectByVisibleText("Pruebas");
		driver.findElement(By.id("priority-field")).clear();
		driver.findElement(By.id("priority-field")).sendKeys("Major");
		driver.findElement(By.linkText("Major")).click();
		driver.findElement(By.cssSelector("option[value=\"10149\"]")).click();
		driver.findElement(By.id("assignee-single-select")).click();
		driver.findElement(By.id("assignee-field")).clear();
		driver.findElement(By.id("assignee-field")).sendKeys("");
		driver.findElement(By.id("assignee-field")).sendKeys("Carlos Alberto Gauto");
		driver.findElement(By.xpath("//ul[@id='all-users']/li/a/span")).click();
		driver.findElement(By.id("customfield_11694")).clear();
		driver.findElement(By.id("customfield_11694")).sendKeys("Nombre Caso de prueba");
		driver.findElement(By.id("components-textarea")).sendKeys("GEDO");
		driver.findElement(By.id("components-suggestions")).click();
		driver.findElement(By.id("versions-textarea")).sendKeys("GEDO 6.26.4");
		driver.findElement(By.linkText("GEDO 6.26.4")).click();
		driver.findElement(By.id("description")).clear();
		driver.findElement(By.id("description")).sendKeys("Descripción del error");
		new Select(driver.findElement(By.id("customfield_11695"))).selectByVisibleText("Pruebas");
		driver.findElement(By.id("attachment_box")).clear();
		driver.findElement(By.id("attachment_box")).sendKeys("C:\\Users\\cargauto\\Pictures\\Archivo1.png");
		driver.findElement(By.id("attachment_box")).clear();
		driver.findElement(By.id("attachment_box")).sendKeys("C:\\Users\\cargauto\\Pictures\\Archivo2.png");
		driver.findElement(By.id("labels-textarea")).sendKeys("JIRA-AUTOMÁTICO AUTOMATIZACIÓN ");
//		driver.findElement(By.id("create-issue-submit")).click();
		// driver.findElement(By.linkText("Cancel")).click();
	}

	@Test
	public void testAltaIncidenciaJira() throws Exception {
		try {
		String usuario = "cargauto";
		String password = "CGMar201703";
		String url = "https://steps.everis.com/jiraproy/login.jsp";
		ingreso(url);
		datosLogin(usuario, password);
		altaIncidenciaJira();
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
	}

	/**
	 * Método extraído, se deberá crear una API que realice estas esperas por
	 * XPath
	 * 
	 * @param xpath
	 * @throws InterruptedException
	 */
	private void waitElementIsPresentByXPath(String xpath) throws InterruptedException {
		for (int second = 0;; second++) {
			if (second >= 60)
				fail("timeout");
			try {
				if (isElementPresent(By.xpath(xpath)))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}
	}

	@After
	public void tearDown() throws Exception {
		// driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}
