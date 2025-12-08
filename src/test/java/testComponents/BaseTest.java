package testComponents;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import abstractComponents.AbstractComponents;


public class BaseTest {
	
	protected WebDriver driver;
	public static ChromeOptions options;
	public static FirefoxOptions firefoxOptions;
	public static EdgeOptions edgeOptions;
	
	public WebDriver initializeDriver() throws IOException {

		switch (AbstractComponents.fetchDataFromProperties("browser")) {
		case "chrome":
			options = new ChromeOptions();
			options.addArguments("guest");
			options.addArguments("--start-maximized");
			driver = new ChromeDriver(options);
			break;

		case "firefox":
			firefoxOptions = new FirefoxOptions();
			firefoxOptions.addArguments("guest");
			firefoxOptions.addArguments("--start-maximized");
			driver = new FirefoxDriver(firefoxOptions);
			break;

		case "edge":
			edgeOptions = new EdgeOptions();
			edgeOptions.addArguments("guest");
			edgeOptions.addArguments("--start-maximized");
			driver = new EdgeDriver(edgeOptions);
			break;
		}

		driver.get(AbstractComponents.fetchDataFromProperties("url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}

	public void closeBrowser() {
		driver.quit();
	}

	
	@BeforeMethod(alwaysRun = true)
	public void launchApplication() throws IOException {
		driver =initializeDriver();
	}
	
	@AfterMethod(alwaysRun = true)
	public void closeApplication() {
		closeBrowser();
	}
}
