package testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import testingfy.pageObject.LandingPage;

public class Base {
	protected LandingPage landingPage;
	protected String product;
	public  WebDriver driver;
	protected ExtentReports extent;
	
	
	
	void intializeDriver() throws IOException{
		String path=System.getProperty("user.dir")+"\\src\\main\\java\\testingfy\\resources\\config.properties";
		FileInputStream fin=new FileInputStream(path);
		Properties property =new Properties();
		property.load(fin);
		
		
		String browserName=System.getProperty("browser")!=null?System.getProperty("browser"):property.getProperty("browser");
		product=property.getProperty("product");
		
		if(browserName.contains("chrome")) {
			WebDriverManager.chromedriver().setup();
			
			if(browserName.contains("headless")) {
				ChromeOptions options=new ChromeOptions();
				options.addArguments("headless");
				driver=new ChromeDriver(options);
				driver.manage().window().setSize(new Dimension(1440,900));
			}else {
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			}
		
		}else if(browserName.contains("edge")) {
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			driver.manage().window().maximize();
			}
		else if(browserName.contains("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			driver.manage().window().maximize();
			}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	
	@BeforeMethod(alwaysRun = true)
	public void launchApplication() throws IOException {
		intializeDriver();
		LandingPage landingPage=new LandingPage(driver);
		landingPage.goTo();
		this.landingPage= landingPage;
	}

	
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
	
	protected String getScreenshot(String testCaseName,WebDriver driver){
		System.out.println(driver.toString());
		File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, new File(System.getProperty("user.dir")+"\\reports\\"+ testCaseName+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return System.getProperty("user.dir")+"\\reports\\"+ testCaseName+".png";
	}
	
	 protected Object[][] getJsonData(String filePath) {
		//Read json to String
			String jsonData="";
			try {
				
				jsonData=	FileUtils.readFileToString(new File(filePath ),StandardCharsets.UTF_8);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Convert String to List of HashMap of String Type
			ObjectMapper objectMapper =new ObjectMapper();
			List<HashMap<String,String>> listOfHashMap = null;
			try {
				listOfHashMap=objectMapper.readValue(jsonData,new TypeReference<List<HashMap<String,String>>>(){
				});
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			Object dataObject[][]=new Object[listOfHashMap.size()][1];
			
			int index=0;
			for(HashMap map:listOfHashMap) {
				dataObject[index++][0]=map;
			}
			
			return dataObject;
		}
}
