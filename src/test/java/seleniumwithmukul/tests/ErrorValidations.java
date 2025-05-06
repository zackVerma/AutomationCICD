package seleniumwithmukul.tests;

import seleniumwithmukul.TestComponents.BaseTest;
import seleniumwithmukul.TestComponents.RetryTestCase;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidations extends BaseTest{
	
	@Test(groups= {"ErrorHandling"})
	public void invalidPasswordErrorValidation() {
	landingPage.loginApplication("devEngineer@test.com","Engineer@17");
	Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
	}
	
	@Test
	public void invalidEmailErrorValidation() {
	landingPage.loginApplication("qaEngineer@test.com","Engineer@18");
	Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
	}
	@Test(groups= {"ErrorHandling"}, retryAnalyzer=RetryTestCase.class)
	public void invalidEmptyValuesErrorValidation() {
	landingPage.loginApplication("","");
	Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
	}
}
