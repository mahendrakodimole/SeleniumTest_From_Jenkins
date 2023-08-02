package testingfy.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testingfy.AbstractCompo.AbstractClass;

public class OrderConfirmationPage extends AbstractClass{
	WebDriver driver;
	public OrderConfirmationPage(WebDriver driver){
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className="hero-primary")
	WebElement confMsg;
	
	public String getConfMsg() {
		return	confMsg.getText();
	}

}
