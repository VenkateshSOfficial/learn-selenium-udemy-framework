package automation.seleniumFrameworkDesign;

import abstractComponents.AbstractComponents;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import seleniumFrameworkDesign.pages.CartPage;
import seleniumFrameworkDesign.pages.LoginPage;
import seleniumFrameworkDesign.pages.ProductCatalogPage;
import testComponents.BaseTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class E2EOrderTest extends BaseTest{
	
	LoginPage login;
	ProductCatalogPage productPage;
	CartPage cartPage;


	@Test(priority=0,dataProvider = "getData")
	public void validateE2E(String email,String password,String product) {
		login=new LoginPage(driver);
		login.loginToApplication(email,password);
		productPage=new ProductCatalogPage(driver);
		cartPage=new CartPage(driver);
		productPage.addProductToCart(product)
		           .clickCartButton()
		           .clickCheckoutButton();
		
		cartPage.enterCountryDropdown("IND", "india")
		        .clickPlaceOrderButtonAndValidateOrderSuccess();
		
	}
	@Test(priority=1,dataProvider = "getMapData")
	public void validateE2EUsingMap(HashMap<String,String> input) {
		login=new LoginPage(driver);
		login.loginToApplication(input.get("email"),input.get("password"));
		productPage=new ProductCatalogPage(driver);
		cartPage=new CartPage(driver);
		productPage.addProductToCart(input.get("product"))
				.clickCartButton()
				.clickCheckoutButton();

		cartPage.enterCountryDropdown("IND", "india")
				.clickPlaceOrderButtonAndValidateOrderSuccess();

	}

	@Test(priority=2,dataProvider = "readJsonFileData")
	public void validateE2EUsingJsonFile(HashMap<String,String> input) {
		login=new LoginPage(driver);
		login.loginToApplication(input.get("email"),input.get("password"));
		productPage=new ProductCatalogPage(driver);
		cartPage=new CartPage(driver);
		productPage.addProductToCart(input.get("product"))
				.clickCartButton()
				.clickCheckoutButton();

		cartPage.enterCountryDropdown("IND", "india")
				.clickPlaceOrderButtonAndValidateOrderSuccess();

	}

	@DataProvider
	public Object[][] getData(){
		return new Object[][] {
				{"venkatesh230691@gmail.com","CENA@wwe2014","ZARA COAT 3"},
				{"venkatesh240691@gmail.com","CENA@wwe2014","ADIDAS ORIGINAL"}
		};
	}

	@DataProvider
	public Object[][] getMapData(){
		HashMap<Object,Object>product1=new HashMap<>();
		product1.put("email","venkatesh230691@gmail.com");
		product1.put("password","CENA@wwe2014");
		product1.put("product","ZARA COAT 3");

		HashMap<Object,Object>product2=new HashMap<>();
		product2.put("email","venkatesh240691@gmail.com");
		product2.put("password","CENA@wwe2014");
		product2.put("product","ADIDAS ORIGINAL");

		return new Object[][]{
				{product1},
				{product2}
		};
	}

	@DataProvider
	public Object[][] readJsonFileData() throws IOException {
		List<HashMap<String, String>> getData = AbstractComponents.readJsonFile();
		return new Object[][] {{getData.get(0)},{getData.get(1)}};
	}
}
