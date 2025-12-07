package testComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import abstractComponents.AbstractComponents;


public class BaseTest {
	
	protected WebDriver driver;
	
	@BeforeTest
	public void launchApplication() throws IOException{
		driver = AbstractComponents.initializeDriver();
	}
	
	@AfterTest
	public void closeApplication() {
		AbstractComponents.closeApplication();
	}
}
