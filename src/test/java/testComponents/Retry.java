package testComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
 int count=0;
 int max=1;
 //helper attribute : retryAnalyzer
 
@Override
public boolean retry(ITestResult result) {
	// TODO Auto-generated method stub
	if(count<max) {
		count++;
		return true;
	}
	return false;
}
 
 
}
