package automation.seleniumFrameworkDesign;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import abstractComponents.AbstractComponents;
import seleniumFrameworkDesign.pages.CartPage;
import seleniumFrameworkDesign.pages.LoginPage;
import seleniumFrameworkDesign.pages.ProductCatalogPage;
import testComponents.BaseTest;

public class E2EOrderTest extends BaseTest{
	
	LoginPage login;
	ProductCatalogPage productPage;
	CartPage cartPage;


	@Test(priority=2,groups={"smoke"},dataProvider = "data")
	public void validateE2E(HashMap<String,String> input/* String email,String password,String product */) {
		login=new LoginPage(driver);
		login.loginToApplication(input.get("email"), input.get("password"));
		productPage=new ProductCatalogPage(driver);
		cartPage=new CartPage(driver);
		
		productPage.getProductByName(input.get("product"));
		productPage.addProductToCart(input.get("product"))
		           .clickCartButton()
		           .clickCheckoutButton();
		
		cartPage.enterCountryDropdown("IND", "india")
		        .clickPlaceOrderButtonAndValidateOrderSuccess();
		
	}
	
	@DataProvider
	public Object[][] data() throws IOException {
		List<HashMap<String,String>> data=AbstractComponents.readJsonFile(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.json");
		
		/*
		 * HashMap<String,String> map=new HashMap<>();
		 * map.put("email","venkatesh230691@gmail.com" ); map.put("pwd",
		 * "CENA@wwe2014"); map.put("product", "ADIDAS ORIGINAL");
		 * 
		 * HashMap<String,String> map1=new HashMap<>();
		 * map1.put("email","venkatesh240691@gmail.com" ); map1.put("pwd",
		 * "Esambalam@2025"); map1.put("product", "ZARA COAT 3");
		 */
		
		/*
		 * return new Object[][] {
		 * {"venkatesh230691@gmail.com","CENA@wwe2014","ADIDAS ORIGINAL"},
		 * {"venkatesh240691@gmail.com","Esambalam@2025","ZARA COAT 3"} };
		 */
		
		
		 return new Object[][] { {data.get(0)}, {data.get(1)} };
		 
	}
}
