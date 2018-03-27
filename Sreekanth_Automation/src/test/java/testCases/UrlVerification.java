package testCases;

import testUtil.Constants;
import testUtil.DataUtil;
import testUtil.Xls_Reader;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import testBase.BaseTest;
import testKeywords.Keyword;

import com.relevantcodes.extentreports.LogStatus;

public class UrlVerification extends BaseTest 
{ 
	@BeforeTest
	public void init()
	{
		xls=new Xls_Reader(Constants.SUITEA_XLS);
		testName="UrlVerification";
	}
	@Test(dataProvider="getData")
	public void loginTest(Hashtable<String,String> data)
	{
		test = rep.startTest(testName);// Start this test
		test.log(LogStatus.INFO,data.toString());// Logging the data for which the test is running
		if(DataUtil.isSkip(xls, testName) || data.get("Runmode").equals("N"))
		{
			test.log(LogStatus.SKIP, "Skipping the test as runmode is N");
			throw new SkipException("Skipping the test as runmode is N");// SKipException via testng
		}
		test.log(LogStatus.INFO, "Starting the Application to Test");// Used for logging
		app=new Keyword(test);
		test.log(LogStatus.INFO, "Executing the keywords");
		app.executeKeywords(testName, xls,data);
		test.log(LogStatus.PASS,"PASS");
		app.getGenericKeywords().screenShot();
		app.getGenericKeywords().closeBrowser();
	}
}
