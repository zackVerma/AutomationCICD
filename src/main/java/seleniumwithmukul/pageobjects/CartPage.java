package seleniumwithmukul.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumwithmukul.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="div.cart h3")
	List<WebElement> cartProducts;
	@FindBy(css=".totalRow button")
	WebElement checkOutButton;
	
	public boolean validateCart(String productName) {		
		Boolean match = cartProducts.stream().anyMatch(product->product.getText().equals(productName));
		return match;
	}
	
	public CheckOutPage checkOut() {
		checkOutButton.click();
		return new CheckOutPage(driver);
	}
}
