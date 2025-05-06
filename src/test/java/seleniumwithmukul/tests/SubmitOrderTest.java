package seleniumwithmukul.tests;

import seleniumwithmukul.TestComponents.BaseTest;
import seleniumwithmukul.TestComponents.RetryTestCase;
import seleniumwithmukul.pageobjects.CartPage;
import seleniumwithmukul.pageobjects.CheckOutPage;
import seleniumwithmukul.pageobjects.ConfirmationPage;
import seleniumwithmukul.pageobjects.OrderPage;
import seleniumwithmukul.pageobjects.ProductCatalogue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class SubmitOrderTest extends BaseTest {

	//Texts to validate and use
	String message = "Thankyou for the order.";
	@Test(dataProvider="getData", groups= {"Purchase"}, retryAnalyzer=RetryTestCase.class)
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {		
		//Product Catalog Page Object
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));	
		productCatalogue.addProductToCart(input.get("productName"));
		
		// Cart Page Object
		CartPage cartPage = productCatalogue.goToCart();
		Boolean match = cartPage.validateCart(input.get("productName"));
		Assert.assertTrue(match);
		
		//Checkout Page
		CheckOutPage checkOutPage = cartPage.checkOut();
		checkOutPage.selectCountry("india");
		
		//Confirmation Page
		ConfirmationPage confirmationPage = checkOutPage.submitOrder();
		String actualMessage = confirmationPage.confirmOrder();
		Assert.assertTrue(actualMessage.equalsIgnoreCase(message));
	}
	
	@Test(dependsOnMethods = {"submitOrder"}, dataProvider="getData")
	public void OrderHistoryTest(HashMap<String, String> input) {
		//Product Catalog Page Object
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		OrderPage orderPage = productCatalogue.goToOders();
		Assert.assertTrue(orderPage.verifyOrderDisplay(input.get("productName")));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> list =  getJsonDataToMap(System.getProperty("user.dir")+
				"//src//test//java//seleniumwithmukul//data//PurchaseOrder.json");
		
		return list.stream().map(value->
					new Object[] {value}).toArray(Object[][]::new);
	}

}
