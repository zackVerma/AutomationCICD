package seleniumwithmukul.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import seleniumwithmukul.TestComponents.BaseTest;
import seleniumwithmukul.pageobjects.CartPage;
import seleniumwithmukul.pageobjects.CheckOutPage;
import seleniumwithmukul.pageobjects.ConfirmationPage;
import seleniumwithmukul.pageobjects.LandingPage;
import seleniumwithmukul.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest {
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
	}
	
	@Given ("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String email, String password) {
		productCatalogue = landingPage.loginApplication(email, password);
	}
	
	@When ("^I add product (.+) from cart$")
	public void I_add_product_from_cart(String productName) {
		productCatalogue.addProductToCart(productName);
	}
	
	@And ("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName) {
		// Cart Page Object
		CartPage cartPage = productCatalogue.goToCart();
		Boolean match = cartPage.validateCart(productName);
		Assert.assertTrue(match);
				
		//Checkout Page
		CheckOutPage checkOutPage = cartPage.checkOut();
		checkOutPage.selectCountry("india");
				
		//Confirmation Page
		confirmationPage = checkOutPage.submitOrder();
	}
	@Then ("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_ConfirmationPage(String message) {
		String actualMessage = confirmationPage.confirmOrder();
		Assert.assertTrue(actualMessage.equalsIgnoreCase(message));
		driver.close();
	}
	
	@Then ("^\"([^\"]*)\" message is displayed")
	public void something_message_id_displayed(String strArg1) throws Throwable {
		Assert.assertEquals(strArg1,landingPage.getErrorMessage());
		driver.close();
	}
}
