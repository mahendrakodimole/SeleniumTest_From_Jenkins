package testingfy.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testingfy.AbstractCompo.AbstractClass;

public class MyCartPage extends AbstractClass{
	WebDriver driver;
	public MyCartPage(WebDriver driver){
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartWrap")
	List<WebElement> cartList;
	
	@FindBy(css=".totalRow button")
	WebElement checkout;
	
	public boolean isProductAddedToCart(String product) {
		return cartList.stream().anyMatch(element->element.findElement(By.tagName("h3")).getText().equals(product));	
	}
	
	public CheckoutPage continueToCheckout() {
		checkout.click();
		return new CheckoutPage(driver);
	}
	
}
