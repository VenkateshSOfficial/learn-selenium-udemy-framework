package seleniumFrameworkDesign.pages;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import abstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents{
	
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".totalRow button")
	WebElement checkoutButton;
	
	@FindBy(css=".form-group input")
	WebElement dropdown;
	
	@FindBy(css=".ta-item.list-group-item.ng-star-inserted")
	List<WebElement> listOfCountries;
	
	@FindBy(css=".actions a")
	WebElement placeOrderButton;
	
	@FindBy(css=".hero-primary")
	WebElement orderSuccessConfirmationMessage;
	
	public void clickCheckoutButton() {
		checkoutButton.click();
	}
	
	public CartPage enterCountryDropdown(String ctry,String ctryName) {
		dropdown.sendKeys(ctry);
		listOfCountries.stream().
		filter(country -> country.getText().
				equalsIgnoreCase(ctryName)).
		findFirst()
		.ifPresent(WebElement::click);
		
		return this;
	}
	
	public void clickPlaceOrderButtonAndValidateOrderSuccess() {
		placeOrderButton.click();
		String orderConfirmationText = orderSuccessConfirmationMessage.getText();
		Assert.assertTrue(orderConfirmationText.equalsIgnoreCase("Thankyou for the order."));
	}
	
}
