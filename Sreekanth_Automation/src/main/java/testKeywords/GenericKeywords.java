package testKeywords;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import testUtil.Constants;

public class GenericKeywords 
{
	public WebDriver driver;
	public Properties prop;
	ExtentTest test;

	public GenericKeywords(ExtentTest test)
	{
		//Constructor of GenericKeywords, called when object of this class is created
		this.test=test;
		prop=new Properties();
		try 
		{
			FileInputStream fs=new FileInputStream(System.getProperty("user.dir")+"\\Resources\\Project.properties");
			prop.load(fs);//properties object will be loaded
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// Deliberately we made the return type to String from void so that we can write a error message of our choice
	public String openBrowser(String browserType) 
	{
		test.log(LogStatus.INFO,"Opening the browser");
		if(browserType.equalsIgnoreCase("Mozilla"))
		{
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"/browserDriver/geckodriver.exe");
			driver=new FirefoxDriver();
			return Constants.PASS;
		}
		else if(browserType.equalsIgnoreCase("Chrome"))
		{
		
			
			
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"/browserDriver/chromedriver.exe");
			DesiredCapabilities capability = DesiredCapabilities.chrome();
			capability.setCapability("chrome.binary", "C:/Program Files (x86)/Google/Chrome/Application/chrome.exe");
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-infobars");
			driver = new ChromeDriver(options);
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			return Constants.PASS;
		}
		else if(browserType.equalsIgnoreCase("Edge"))
		{
			System.setProperty("webdriver.edge.driver",System.getProperty("user.dir")+"/browserDriver/MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			return Constants.PASS;

		}
		else
		{
			test.log(LogStatus.FAIL,"Could not open the browser");
			return Constants.FAIL;
		}

	}
	public String navigate(String url) 
	{
		test.log(LogStatus.INFO, "Navigatin to --" +prop.getProperty(url));
		driver.get(prop.getProperty(url));
		return Constants.PASS;
	}
	public String click(String Locator) 
	{
		test.log(LogStatus.INFO,"Clicking on --"+prop.getProperty(Locator));
		WebElement e=getElement(Locator);
		e.click();
		return Constants.PASS;
	}
	public String title(String expectedText) 
	{
		String actualText=driver.getTitle();
		System.out.println(".....  :"+actualText+"    :"+expectedText);
		test.log(LogStatus.INFO, ".....  :"+actualText+"    :"+expectedText);
		if(actualText.contains(expectedText))
		{
		
			return Constants.PASS;
		}
		else
		{
			return Constants.FAIL;
		}
	}
	public String clear(String Locator) 
	{
		test.log(LogStatus.INFO,"Clicking on --"+prop.getProperty(Locator));
		WebElement e=getElement(Locator);
		e.clear();
		return Constants.PASS;
	}
	public String input(String locator, String data) 
	{
		test.log(LogStatus.INFO,"Typing the value --"+prop.getProperty(locator));
		WebElement e=getElement(locator);
		e.clear();
		e.sendKeys(data);
		return Constants.PASS;
	}
	public String dropDown(String locator,String data)
	{
		WebElement e=getElement(locator);
		Select dd_select=new Select(e);
		dd_select.selectByVisibleText(""+data);
		System.out.println("Select the data as : "+data);
		return Constants.PASS;
	}
	public String dropDownValue(String locator,String data)
	{
		WebElement e=getElement(locator);
		Select dd_select=new Select(e);
		dd_select.selectByIndex(Integer.parseInt(data));
		System.out.println("Select the data as : "+data);
		return Constants.PASS;
	}
	
	public String dropDownVerify(String locator,String data)
	{
		WebElement e=getElement(locator);
		Select dd_select=new Select(e);
		String data1=dd_select.getFirstSelectedOption().getText();
		System.out.println("Select the data as : actual - "+data1+"expected - "+data);
		if(data1.contains(data))
		{
		
			return Constants.PASS;
		}
		else
		{
			return Constants.FAIL;
		}
		
		
	}
	
	public String dropDownCount(String locator,String data)
	{
		WebElement e=getElement(locator);
		Select dd_select=new Select(e);
		List<WebElement> e1=dd_select.getOptions();
		int count=e1.size();
		System.out.println("Total Count is : "+count);
		if(count==Integer.parseInt(data))
		{
		
			return Constants.PASS;
		}
		else
		{
			return Constants.FAIL;
		}
	}
	public String closeBrowser()
	{
		test.log(LogStatus.INFO, "Closing Browser");
		driver.close();
		return Constants.PASS;
	}

	/***********************validaton Keywords***************/
	public String verifyText(String locator,String expectedText) 
	{
		WebElement e=getElement(locator);
		String actualText=e.getText();
		System.out.println(".....  :"+actualText+"    :"+expectedText);
		test.log(LogStatus.INFO, ".....  :"+actualText+"    :"+expectedText);
		//if(actualText.equals(expectedText))
		if(actualText.contains(expectedText))
		{
		
			return Constants.PASS;
		}
		else
		{
			return Constants.FAIL;
		}
	}
	
	public String alertHandling() 
	{
		Alert a=driver.switchTo().alert();
		try
		{
			a.accept();
			a.accept();
			
		}
		catch(Exception e)
		{
			System.out.println("No Alert Present");
		}
		return Constants.PASS;
	}

	public String waiting()
	{
		try 
		{
			Thread.sleep(3000);
		} 
		catch (NumberFormatException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Constants.PASS;
	}



	/****************************Utility************************************************************/

	//To check whether element is present or not, every locator goes through this function
	public  WebElement getElement(String Locator) 
	{
		WebElement e=null;
		try
		{
			if(Locator.endsWith("_xpath"))
			{
				e=driver.findElement(By.xpath(prop.getProperty(Locator)));
				System.out.println(e);
			}
			else if(Locator.endsWith("_id")){
				e=driver.findElement(By.id(prop.getProperty(Locator)));
			}
			else if(Locator.endsWith("_name")){
				e=driver.findElement(By.name(prop.getProperty(Locator)));
			}
			else if(Locator.endsWith("_linkText")){
				e=driver.findElement(By.linkText(prop.getProperty(Locator)));
			}

		}
		catch(Exception ex)
		{
			reportFailure("Failure in element extraction--"+Locator);
			Assert.fail("Failure in extracting the locator --"+prop.getProperty(Locator));
		}

		return e;
	}


	public  List<WebElement> getElements(String Locator) 
	{
		List<WebElement> e=null;
		try
		{
			if(Locator.endsWith("_xpath"))
			{
				e=driver.findElements(By.xpath(prop.getProperty(Locator)));
				//System.out.println("Locator value is :"+prop.getProperty(Locator));
			}
			else if(Locator.endsWith("_id")){
				e=driver.findElements(By.id(prop.getProperty(Locator)));
			}
			else if(Locator.endsWith("_name")){
				e=driver.findElements(By.name(prop.getProperty(Locator)));
			}
			else if(Locator.endsWith("_linkText")){
				e=driver.findElements(By.linkText(prop.getProperty(Locator)));
			}
		}
		catch(Exception ex)
		{
			reportFailure("Failure in element extraction--"+Locator);
			Assert.fail("Failure in extracting the locator --"+prop.getProperty(Locator));
		}

		return e;
	}
	public String scroll(String locator){

		/*JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");*/
		WebElement element = getElement(locator);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

		return Constants.PASS;

	}

	/*****************************Reporting Functions******************************************/
	public void reportFailure(String failureMessage)
	{
		// Call this func where ever you want to fail the test
		screenShot();
		test.log(LogStatus.FAIL,failureMessage);
		closeBrowser();
	}

	public void reportFailure1(String failureMessage)
	{
		// Call this func where ever you want to fail the test
	
		test.log(LogStatus.FAIL,failureMessage);
	
	}


	public String printScreenShot()
	{
		screenShot();
		return Constants.PASS;

	}
	public void screenShot()
	{
		//decide name - time stamp
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
		String path=Constants.SCREENSHOT_PATH+screenshotFile;
		//take screenshot
		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//byte[] screenshot_file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
		try 
		{
			FileUtils.copyFile(srcFile,new File(path));
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		//embed it in the reports
		test.log(LogStatus.INFO,test.addScreenCapture(path));
	}
}