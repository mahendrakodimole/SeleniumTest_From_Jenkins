package testingfy.pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testingfy.AbstractCompo.AbstractClass;

public class ProductCatelogue extends AbstractClass{
	WebDriver driver;
	public ProductCatelogue(WebDriver driver){
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3 .card")
	List<WebElement> products;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement loginBtn;
	
	@FindBy(className="\"ngx-spinner-overlay\"")
	WebElement spinnerOverlay;
	
	
	public WebElement getProduct(String product) {
		WebElement productCard=	products.stream().filter(n->n.findElement(By.tagName("h5")).getText().equals(product)).findFirst().orElse(null);
	return productCard;
	}
	
	public void addToCart(String product) {
		getProduct(product).findElement(By.cssSelector(".card-body button:last-of-type")).click();
		waitForElementToDissappear(spinnerOverlay);
	}
	
}
