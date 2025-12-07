package automation.seleniumFrameworkDesign;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import seleniumFrameworkDesign.pages.LoginPage;

public class StandaloneTest {
	public WebDriver driver;
	public ChromeOptions options;
	public WebDriverWait explicitlyWait;
	
	public void explicitlyWait(WebElement ele) {
		explicitlyWait=new WebDriverWait(driver,Duration.ofSeconds(5));
		explicitlyWait.until(ExpectedConditions.visibilityOf(ele));
		System.out.println("waiting...");
	}
	
	public void explicitlyWaitForInvisibilityOfElement(WebElement ele) {
		explicitlyWait=new WebDriverWait(driver,Duration.ofSeconds(5));
		explicitlyWait.until(ExpectedConditions.invisibilityOf(ele));
		System.out.println("waiting...");
	}

	@BeforeTest
	public void launchBrowser() {
		options = new ChromeOptions();
		options.addArguments("guest");
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		LoginPage login=new LoginPage(driver);
	}

	@Test
	public void login() {
		driver.findElement(By.cssSelector("#userEmail")).sendKeys("venkatesh230691@gmail.com");
		driver.findElement(By.cssSelector("#userPassword")).sendKeys("CENA@wwe2014");
		driver.findElement(By.cssSelector(".btn.btn-block.login-btn")).click();
	}

	@Test(dependsOnMethods = { "login" })
	public void addToCart() {
		List<WebElement> items = driver.findElements(By.xpath("//*[@class='card-body']"));
		/*
		 * for(int i=0;i<items.size();i++) { WebElement eachItem =
		 * items.get(i).findElement(By.xpath("./h5/b"));
		 * System.out.println(eachItem.getText()); }
		 */
		
		WebElement product = items.stream().
				             filter(item->item.findElement(By.xpath("./h5/b")).
				            		 getText().
				            		 equalsIgnoreCase("ADIDAS ORIGINAL")).
				                     findFirst().orElse(null);
		
		product.findElement(By.xpath("//*[@class='card-body']/button[2]")).click();
		
		WebElement productAddToast = driver.findElement(By.cssSelector("#toast-container"));
		explicitlyWait(productAddToast);
		Assert.assertTrue(productAddToast.isDisplayed());
		explicitlyWaitForInvisibilityOfElement(productAddToast);
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		driver.findElement(By.cssSelector(".totalRow button")).click();
		driver.findElement(By.cssSelector(".form-group input")).sendKeys("IND");
		
		//
		
		List<WebElement> countries = driver.findElements(By.cssSelector(".ta-item.list-group-item.ng-star-inserted"));
		/*
		 * for(int i=0;i<countries.size();i++) {
		 * System.out.println(countries.get(i).getText());
		 * if(countries.get(i).getText().equalsIgnoreCase("india")) {
		 * countries.get(i).click(); break; } }
		 */
		countries.stream().
		          filter(country->country.getText().equalsIgnoreCase("india")).
		          findFirst().
		          ifPresent(WebElement::click);
		
		driver.findElement(By.cssSelector(".actions a")).click();
		String orderConfirmationText = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(orderConfirmationText.equalsIgnoreCase("Thankyou for the order."));
	}
	
	
	

	@AfterTest
	public void quitBrowser() throws InterruptedException{
		Thread.sleep(3000);
		driver.quit();
	}
}
