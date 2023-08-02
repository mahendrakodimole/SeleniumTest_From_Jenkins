package testingfy.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testingfy.AbstractCompo.AbstractClass;

public class LandingPage extends AbstractClass{
WebDriver driver;
	
	public LandingPage(WebDriver driver){
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement email;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement loginBtn;
	
	@FindBy(css=".toast-error")
	WebElement loginErrorToast;
	
	
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public ProductCatelogue login() {
		email.sendKeys("selenium@testing.com");
		password.sendKeys("Testing@1");
		loginBtn.click();
		return new ProductCatelogue(driver);
	}
	
	public ProductCatelogue login(String username,String passwordTxt) {
		email.sendKeys(username);
		password.sendKeys(passwordTxt);
		loginBtn.click();
		return new ProductCatelogue(driver);
	}
	
	public String getLoginErrorMsg() {
	return	loginErrorToast.getText();
	}
	
	
	
	
	
}
