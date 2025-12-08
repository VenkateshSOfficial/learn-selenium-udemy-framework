package automation.seleniumFrameworkDesign;

import org.testng.annotations.Test;

import seleniumFrameworkDesign.pages.LoginPage;
import seleniumFrameworkDesign.pages.ProductCatalogPage;
import testComponents.BaseTest;

public class ValidateLoginTest extends BaseTest{
	LoginPage login;
	ProductCatalogPage productPage;
	
	@Test(priority=0,groups={"smoke"})
	public void validateLoginSuccess() {
		login=new LoginPage(driver);
		productPage=new ProductCatalogPage(driver);
		
		login.loginToApplication("venkatesh230691@gmail.com", "CENA@wwe2014");
		productPage.validateLoginSuccess();
	}
	
	@Test(priority=1)
	public void validateLoginFail() {
		login=new LoginPage(driver);
		login.validateErrorToastMessage("venkatesh230691@gmail.com", "123");
	}
}
