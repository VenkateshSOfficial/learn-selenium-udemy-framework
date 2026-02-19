package abstractComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

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
	
	public static List<HashMap<String, String>> readJsonFile() throws IOException {
		String jsonContent = FileUtils.readFileToString(
				new File(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\data.json"),
				StandardCharsets.UTF_8);
        return new ObjectMapper().readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>()
                {});
	}

	public String screenshot(String testCaseName) throws IOException {
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination=new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source,destination);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}
}
