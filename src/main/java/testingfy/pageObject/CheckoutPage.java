package testingfy.pageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testingfy.AbstractCompo.AbstractClass;

public class CheckoutPage extends AbstractClass{
	WebDriver driver;
	public CheckoutPage(WebDriver driver){
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input[placeholder*='Country']")
	WebElement countryInput;
	
	@FindBy(css=".form-group .ta-results .ta-item")
	List<WebElement> countryList;
	
	@FindBy(xpath="//a[text()='Place Order ']")
	WebElement placeOrder;
	
	public OrderConfirmationPage placeOrder() {
		placeOrder.click();
	return	new OrderConfirmationPage(driver);
	}

	public void selectCountry(String country) {
		actions.sendKeys(countryInput,country).perform();
		WebElement countryEle=countryList.stream().filter(n->n.getText().equalsIgnoreCase(country)).findFirst().orElse(null);
		if(countryEle!=null) countryEle.click();
	}
	
}
