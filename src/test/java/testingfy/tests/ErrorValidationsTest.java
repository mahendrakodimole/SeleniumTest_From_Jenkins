package testingfy.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import testComponents.Base;
import testComponents.CustomListener;
import testComponents.Retry;
import testingfy.pageObject.*;


public class ErrorValidationsTest extends Base{
	@Test(groups= {"errorValidations"},retryAnalyzer = Retry.class)
	void loginErroValidation()  throws IOException {
		landingPage.login("selenium@testing.com","Testing@12");
		String loginErrorMsg=landingPage.getLoginErrorMsg();
		Assert.assertEquals(loginErrorMsg, "Incorrectdefect email or password.");
	}
	
@Test(groups= {"errorValidations"})
void productErrorValidation()  throws IOException {
		ProductCatelogue productCatelogue=landingPage.login("selenium1@testing.com","Testing@1");
		productCatelogue.addToCart(product);
		MyCartPage myCart=productCatelogue.goToCart();
		Assert.assertFalse(myCart.isProductAddedToCart(product+" "));
	}
}
