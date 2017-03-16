package test.selenium.util;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.Augmenter;

public class PhantomJSDriverScreenshot {
	private WebDriver driver;
	
	public PhantomJSDriverScreenshot() {
	
	}
	public void screenshot(String ruta, String patron) throws IOException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		System.out.println("Tomando captura de pantalla.");
//		driver = (PhantomJSDriver) new Augmenter().augment(driver);
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		System.out.println("File:" + srcFile);
		try {
			String rutaCaptura = ".\\" + ruta + "\\"+ patron +"_" + dateFormat.format(date) + ".png";
			FileUtils.copyFile(srcFile, new File(rutaCaptura));
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		System.out.println("Captura de pantalla realizada.");
	}
}
