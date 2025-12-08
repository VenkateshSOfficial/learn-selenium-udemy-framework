package abstractComponents;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponents {
	WebDriver driver;
	public WebDriverWait explicitlyWait;
	

	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
	}

	public void explicitlyWait(WebElement ele) {
		explicitlyWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		explicitlyWait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void explicitlyWaitForInvisibilityOfElement(WebElement ele) {
		explicitlyWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		explicitlyWait.until(ExpectedConditions.invisibilityOf(ele));
	}

	public static String fetchDataFromProperties(String key) throws IOException {
		Properties properties = new Properties();
		String filePath = System.getProperty("user.dir") + "\\src\\main\\java\\resources\\global.properties";

		try (FileInputStream fis = new FileInputStream(filePath)) {
			properties.load(fis);
		} catch (FileNotFoundException e) {
			throw new IOException("Properties file not found at path: " + filePath, e);
		}

		return properties.getProperty(key);
	}
}
