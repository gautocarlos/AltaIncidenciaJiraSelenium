package test.selenium.frontend.jira;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.WebDriverWait;


public class IngresoUrl {
	private WebDriver driver;
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
		driver = new FirefoxDriver(profile);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void datosLogin(String usuario, String password) {
		driver.findElement(By.xpath("//*[@placeholder='Usuario']")).clear();
		driver.findElement(By.xpath("//*[@placeholder='Usuario']")).sendKeys(usuario);
		driver.findElement(By.xpath("//*[@placeholder='Contraseña']")).clear();
		driver.findElement(By.xpath("//*[@placeholder='Contraseña']")).sendKeys(password);
		driver.findElement(By.xpath("//*[@class='btn btn-default z-button']")).click();
	}

	@Test
	public void testIngresoUrl() throws Exception {

	}



	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

}
