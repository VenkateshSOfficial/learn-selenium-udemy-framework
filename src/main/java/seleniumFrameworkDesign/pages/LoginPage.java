package seleniumFrameworkDesign.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import abstractComponents.AbstractComponents;

public class LoginPage extends AbstractComponents{
	WebDriver driver;
	String errorMsg;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="#userEmail")
	WebElement email;
	
	@FindBy(css="#userPassword")
	WebElement password;
	
	@FindBy(css=".btn.btn-block.login-btn")
	WebElement loginButton;
	
	@FindBy(css=".toast-bottom-right")
	WebElement errorToastMessage;
	
	public ProductCatalogPage loginToApplication(String mailId,String pwd) {
		email.sendKeys(mailId);
		password.sendKeys(pwd);
		loginButton.click();
		return new ProductCatalogPage(driver);
	}
	
	public void validateErrorToastMessage(String mailId,String pwd) {
		email.sendKeys(mailId);
		password.sendKeys(pwd);
		loginButton.click();
		explicitlyWait(errorToastMessage);
		Assert.assertTrue(errorToastMessage.isDisplayed());
		errorMsg = errorToastMessage.getText();
		Assert.assertEquals(errorMsg, "Incorrect email or password.");
	}

}
