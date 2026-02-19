package seleniumFrameworkDesign.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import abstractComponents.AbstractComponents;

public class ProductCatalogPage extends AbstractComponents{
	WebDriver driver;

	public ProductCatalogPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@class='card-body']/h5/b")
	List<WebElement> items;
	
	@FindBy(css="#toast-container")
	WebElement toast;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cartButton;

	@FindBy(xpath = "//*[@class='card-body']/button[2]")
	List<WebElement> productsCartButton;

	@FindBy(css=".fa.fa-sign-out")
	WebElement signoutButton;
	
	public ProductCatalogPage addProductToCart(String productName) {
		for(int i=0;i<items.size();i++){
			if(items.get(i).getText().toLowerCase().equalsIgnoreCase(productName)){
				productsCartButton.get(i).click();
			}
		}
		explicitlyWait(toast);
		Assert.assertTrue(toast.isDisplayed());
		explicitlyWaitForInvisibilityOfElement(toast);
		return this;
	}
	
	public CartPage clickCartButton() {
		cartButton.click();
		return new CartPage(driver);
	}
	
	public void validateLoginSuccess() {
		explicitlyWait(signoutButton);
		Assert.assertTrue(signoutButton.isDisplayed());
	}
	
}
