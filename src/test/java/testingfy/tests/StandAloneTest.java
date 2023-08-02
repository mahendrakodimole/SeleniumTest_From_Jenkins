package testingfy.tests;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {
	static String product="ZARA COAT 3";

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		
		driver.get("https://rahulshettyacademy.com/client");

		driver.findElement(By.id("userEmail")).sendKeys("selenium@testing.com");
		driver.findElement(By.id("userPassword")).sendKeys("Testing@1");
		driver.findElement(By.id("login")).click();
		
		List<WebElement> cards=driver.findElements(By.cssSelector(".mb-3 .card"));
		WebElement card=cards.stream().filter(n->n.findElement(By.tagName("h5")).getText().equals(product)).findFirst().orElse(null);
		
		card.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.className("ngx-spinner-overlay"))));
		driver.findElement(By.cssSelector("button[routerlink*='/cart']")).click();
		
		List<WebElement> cartList=driver.findElements(By.cssSelector(".cartWrap"));
		boolean match=cartList.stream().anyMatch(element->element.findElement(By.tagName("h3")).getText().equals(product) );

		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions actions=new Actions(driver);
		actions.sendKeys(driver.findElement(By.cssSelector("input[placeholder*='Country']")), "india").perform();
		
		WebElement country=driver.findElements(By.cssSelector(".form-group .ta-results .ta-item")).stream().filter(n->n.getText().equalsIgnoreCase("india")).findFirst().orElse(null);
		
		if(country!=null) country.click();
		
		driver.findElement(By.xpath("//a[text()='Place Order ']")).click();
		String confirmMsg=driver.findElement(By.className("hero-primary")).getText();
		System.out.println(confirmMsg);
		Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
	}

}
