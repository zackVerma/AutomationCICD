package seleniumwithmukul.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumwithmukul.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory
	@FindBy(css=".mb-3")
	List<WebElement> products;
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	@FindBy(css="#toast-container")
	WebElement toastMessage;
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(products.get(0));
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement productZara = getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b"))
		.getText().equals(productName))
		.findFirst().orElse(null);
		return productZara;
	}
	
	public void addProductToCart(String productName) {
		getProductByName(productName).findElement(
				addToCart)
				.click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
	}
}
