package automation.seleniumFrameworkDesign;

import org.testng.annotations.Test;

import seleniumFrameworkDesign.pages.CartPage;
import seleniumFrameworkDesign.pages.LoginPage;
import seleniumFrameworkDesign.pages.ProductCatalogPage;
import testComponents.BaseTest;

public class E2EOrderTest extends BaseTest{
	
	LoginPage login;
	ProductCatalogPage productPage;
	CartPage cartPage;


	@Test(priority=2,groups={"smoke"})
	public void validateE2E() {
		login=new LoginPage(driver);
		login.loginToApplication("venkatesh230691@gmail.com", "CENA@wwe2014");
		productPage=new ProductCatalogPage(driver);
		cartPage=new CartPage(driver);
		
		productPage.getProductByName("ADIDAS ORIGINAL");
		productPage.addProductToCart("ADIDAS ORIGINAL")
		           .clickCartButton()
		           .clickCheckoutButton();
		
		cartPage.enterCountryDropdown("IND", "india")
		        .clickPlaceOrderButtonAndValidateOrderSuccess();
		
	}
}
