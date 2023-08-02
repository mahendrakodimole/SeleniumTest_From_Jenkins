package testingfy.AbstractCompo;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testingfy.pageObject.MyCartPage;
import testingfy.pageObject.OrdersPage;

public class AbstractClass {
	
	protected WebDriver driver;
	private WebDriverWait wait;
	protected Actions actions;
	
	public AbstractClass(WebDriver driver) {
		this.driver=driver;
		wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		actions=new Actions(driver);
	}
	
	@FindBy(css="button[routerlink*='/cart']")
	WebElement cart;
	
	@FindBy(css="button[routerlink*='/myorders']")
	WebElement orders;
	
	public void waitForElementToDissappear(WebElement element){
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public MyCartPage goToCart() {
		cart.click();
		return new MyCartPage(driver);
	}
	
	public OrdersPage goToOrders() {
		orders.click();
		return new OrdersPage(driver);
	}
}
