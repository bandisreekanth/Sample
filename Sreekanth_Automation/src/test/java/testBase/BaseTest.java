package testBase;

import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import extentManager.ExtentManager;
import testKeywords.Keyword;
import testUtil.DataUtil;
import testUtil.Xls_Reader;

public class BaseTest 
{
	//ExtentReports rep=ExtentManager.getInstance();// Gives the object of Extent reports
	public ExtentReports rep=ExtentManager.getInstance();//Making it public as it can be accessible in every package
	public ExtentTest test;		
	public Keyword app; // Declaring it globally as it can be used in After test for quiting the browser as well
	public Xls_Reader xls;
	public String testName;
	public String totalTestCases[];
	public int count;
	public int i;


	@AfterTest
	public void quit()
	{
		if(rep!=null)
		{
			rep.endTest(test);
			rep.flush();
			// The above two lines are compulsory else reports will not be generated
		}
		
	}
	@DataProvider
	public Object[][]getData()
	{
		return DataUtil.getData(xls,testName);
	}

}
