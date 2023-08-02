package testComponents;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.Listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class CustomListener extends Base implements ITestListener {
	
	
	private ExtentReports extent;
	private ExtentTest test;
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>();
	
	
	{
		 ExtentSparkReporter reporter= new ExtentSparkReporter (System.getProperty("user.dir")+"\\test-output\\extent_report\\index.html");

		  reporter.config().setTheme(Theme.DARK);
		  reporter.config().enableOfflineMode(true);
		  reporter.config().setReportName("Testingfy Report");
		 
		  extent =new ExtentReports();
		 extent.attachReporter(reporter);
		 extent.setSystemInfo("Environment", "QA");
		  extent.setSystemInfo("Tester", "mahendrakodimole");
		  extent.setSystemInfo("Platform", "Windows 10");
	}
	
	 
	public void onTestStart(ITestResult result) {
		 test = extent.createTest(result.getMethod().getMethodName());
		 extentTest.set(test);
	  }

	
	   public void onTestSuccess(ITestResult result) {
		
		   extentTest.get().pass("Test Passed");
		   
		  // test.pass("Test passed");
	  }

	  
	  public  void onTestFailure(ITestResult result) {
		  String testCaseName=result.getMethod().getMethodName();
		  
		  WebDriver driver=null;
		  
			try {
				driver =(WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 extentTest.get().fail(result.getThrowable());
			 extentTest.get().addScreenCaptureFromPath( getScreenshot(testCaseName,driver),testCaseName);
		 
		//	 test.addScreenCaptureFromPath( getScreenshot(testCaseName,driver),testCaseName);
		//	 test.fail(result.getThrowable());

	  }

	
	  public  void onTestSkipped(ITestResult result) {
		  
		 //not implemented
		  extentTest.get().skip("Test Skipped");
	  }

	
	  public  void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	    // not implemented
	  }

	  public  void onTestFailedWithTimeout(ITestResult result) {
	    onTestFailure(result);
	  }

	  public  void onStart(ITestContext context) {
		  
		 //same functionalities achived using non static block
		  
	  }

	  public  void onFinish(ITestContext context) {
		  System.out.println("On Finish");
		  if(extent!=null)
			  extent.flush();
	  }
	  
		

}
