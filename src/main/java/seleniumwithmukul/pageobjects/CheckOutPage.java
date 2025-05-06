package seleniumwithmukul.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumwithmukul.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement countryInput;
	@FindBy(xpath="//span[text()=' India']/parent::button")
	WebElement countryOption;
	@FindBy(css="button.ta-item:nth-of-type(2)")
	WebElement countryOptionButton;
	@FindBy(className="action__submit")
	WebElement submitButton;
	
	public void selectCountry(String countryName) {
		super.action.sendKeys(countryInput, countryName).build().perform();
		waitForElementTobeClickable(countryOption);
		countryOptionButton.click();
	}
	
	public ConfirmationPage submitOrder() {
		waitForElementTobeClickable(submitButton);
		scrollToElementwithJS(submitButton);
		clickOnElementwithJS(submitButton);
		return new ConfirmationPage(driver);
	}

}
