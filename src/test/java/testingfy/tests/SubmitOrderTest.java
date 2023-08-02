package testingfy.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.HashMap;
import testComponents.*;
import testingfy.pageObject.*;


public class SubmitOrderTest extends Base{

	@Test(dataProvider = "dataProvider")
	void submitOrder(HashMap<String, String> input)  throws IOException {
		System.out.println("submitOrder"+input.get("email"));
		System.out.println("User Name:"+input.get("password"));
		System.out.println("Password :"+input.get("product"));
		
		ProductCatelogue productCatelogue=landingPage.login(input.get("email"),input.get("password"));
	
		productCatelogue.addToCart(input.get("product"));
		MyCartPage myCart=productCatelogue.goToCart();
		
		AssertJUnit.assertTrue(myCart.isProductAddedToCart(input.get("product")));
		
		CheckoutPage checkoutPage=myCart.continueToCheckout();
		checkoutPage.selectCountry("india");
		OrderConfirmationPage orderConfPage=checkoutPage.placeOrder();
		
		String confrimationMsg=orderConfPage.getConfMsg();
		AssertJUnit.assertTrue(confrimationMsg.equals("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dependsOnMethods="submitOrder",dataProvider = "dataProvider")
	void orderHistoryTest(HashMap<String, String> input) {
		System.out.println("orderHistoryTest");
		System.out.println("submitOrder"+input.get("email"));
		System.out.println("User Name:"+input.get("password"));
		System.out.println("Password :"+input.get("product"));
		
		ProductCatelogue prooductCatelogue=landingPage.login(input.get("email"),input.get("password"));
		OrdersPage ordersPage=prooductCatelogue.goToOrders();
		boolean isorderPlaced=ordersPage.isOrderPlaced(input.get("product"));
		AssertJUnit.assertTrue(isorderPlaced);
	}
	
	@DataProvider
	Object[][] dataProvider() {	
		
//		HashMap<String ,String > map=new HashMap<>();
//		map.put("selenium@testing.com", "Testing@1");
//		map.put("selenium1@testing.com", "Testing@1");
//		
//		int rows=map.size();
//		Object[][] data=new Object[rows][2];
//		
//		int index=0;
//		
//		for(Map.Entry<String, String> entry:map.entrySet()) {
//			data[index][0]=entry
//					.getKey();
//			data[index++][1]=entry.getValue();
//		}
//		
//		Iterator<Entry<String, String>> it=map.entrySet().iterator();
//		
//		while(it.hasNext()) {
//			Entry<String, String> values=it.next();
//			data[index][0]=values.getKey();
//			data[index++][1]=values.getValue();
//		}
		
		return getJsonData(System.getProperty("user.dir")+"\\src\\test\\java\\testingfy\\data\\loginData.json");
	}
	
	
}
