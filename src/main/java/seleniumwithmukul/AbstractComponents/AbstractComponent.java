package seleniumwithmukul.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import seleniumwithmukul.pageobjects.CartPage;
import seleniumwithmukul.pageobjects.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	WebDriverWait wait;
	protected Actions action;
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		this.action = new Actions(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(7));
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartButton;
	@FindBy(css="[routerlink*='myorders']")
	WebElement ordersButton;
	
	public CartPage goToCart() {
		cartButton.click();
		return new CartPage(driver);
	}
	
	public OrderPage goToOders() {
		ordersButton.click();
		return new OrderPage(driver);
	}

	public void waitForElementToAppear(WebElement ele) {
	wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void waitForElementToDisappear(WebElement ele) {
		wait.until(ExpectedConditions.invisibilityOf(ele));
		}
	
	public void waitForElementTobeClickable(WebElement ele) {
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	public void scrollToElementwithJS(WebElement ele) {
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", ele);
	}
	
	public void clickOnElementwithJS(WebElement ele) {
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", ele);
	}
	
}
