package testingfy.pageObject;

import java.util.List;
import java.util.Optional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testingfy.AbstractCompo.AbstractClass;

public class OrdersPage extends AbstractClass{
	public OrdersPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".table tbody tr td:nth-child(3)")
	List<WebElement> orders;
	
	public boolean isOrderPlaced(String product) {
		return orders.stream().anyMatch(order->order.getText().equalsIgnoreCase(product));
	}

}
