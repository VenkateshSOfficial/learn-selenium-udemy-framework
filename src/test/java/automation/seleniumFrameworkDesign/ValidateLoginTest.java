package automation.seleniumFrameworkDesign;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import seleniumFrameworkDesign.pages.LoginPage;
import seleniumFrameworkDesign.pages.ProductCatalogPage;
import testComponents.BaseTest;

public class ValidateLoginTest extends BaseTest{
	LoginPage login;
	ProductCatalogPage productPage;
	
	@Test(priority=0,dataProvider = "data")
	public void validateLoginSuccess(String mail,String pwd) {
		login=new LoginPage(driver);
		productPage=new ProductCatalogPage(driver);
		
		login.loginToApplication(mail, pwd);
		productPage.validateLoginSuccess();
	}
	
	@Test(priority=1)
	public void validateLoginFail() {
		login=new LoginPage(driver);
		login.validateErrorToastMessage("venkatesh230691@gmail.com", "123");
	}
	
	@DataProvider
	public Object[][] data() {
		return new Object[][] {
			{"venkatesh230691@gmail.com","CENA@wwe2014"},
		    {"venkatesh240691@gmail.com","Esambalam@2025"}
		};
	}
}
