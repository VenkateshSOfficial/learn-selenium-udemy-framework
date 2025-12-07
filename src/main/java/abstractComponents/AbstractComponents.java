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

public class AbstractComponents{
	static WebDriver driver;
	public WebDriverWait explicitlyWait;
	public static ChromeOptions options;
	public static FirefoxOptions firefoxOptions;
	public static EdgeOptions edgeOptions;
	
	public AbstractComponents(WebDriver driver) {
		this.driver=driver;
	}
	
	public void explicitlyWait(WebElement ele) {
		explicitlyWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		explicitlyWait.until(ExpectedConditions.visibilityOf(ele));
		System.out.println("waiting...");
	}

	public void explicitlyWaitForInvisibilityOfElement(WebElement ele) {
		explicitlyWait = new WebDriverWait(driver, Duration.ofSeconds(5));
		explicitlyWait.until(ExpectedConditions.invisibilityOf(ele));
		System.out.println("waiting...");
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

	public static WebDriver initializeDriver() throws IOException {
		
		switch(fetchDataFromProperties("browser")){
		case  "chrome":
			options = new ChromeOptions();
			options.addArguments("guest");
			options.addArguments("--start-maximized");
			driver = new ChromeDriver(options);
			break;
			
	    case "firefox":
	    	firefoxOptions=new FirefoxOptions();
	    	firefoxOptions.addArguments("guest");
	    	firefoxOptions.addArguments("--start-maximized");
			driver=new FirefoxDriver(firefoxOptions);
			break;
			
	    case "edge":
	    	edgeOptions=new EdgeOptions();
	    	edgeOptions.addArguments("guest");
	    	edgeOptions.addArguments("--start-maximized");
	    	driver=new EdgeDriver(edgeOptions);
	    	break;
		}
		
		driver.get(fetchDataFromProperties("url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	public static void closeApplication() {
		driver.quit();
	}

}
