package testKeywords;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import testUtil.Constants;

public class AppKeywords extends GenericKeywords 
{
	public AppKeywords(ExtentTest test) 
	{
		super(test);
	}

	public String handleMouseOver(String Locator) 
	{
		test.log(LogStatus.INFO,"handling mouseover --"+prop.getProperty(Locator));
		Actions a=new Actions(driver);
		WebElement e=getElement(Locator);
		a.moveToElement(e).build().perform();
		return Constants.PASS;
	}

	@SuppressWarnings("deprecation")
	public String inputCaptcha(String object) 
	{
		String s="";
		// TODO Auto-generated method stub
		DataInputStream dis=new DataInputStream(System.in);
		System.out.println("Enter the captcha");
		try 
		{
			s=dis.readLine();
		} 
		catch (IOException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		WebElement e=getElement(object);
		
		e.sendKeys(s);
		return Constants.PASS;
	}

	public String switchToFrame() {
		// TODO Auto-generated method stub
		driver.switchTo().frame(0);
		return Constants.PASS;
	}

	public String switchToDefaultFrame() {
		// TODO Auto-generated method stub
		driver.switchTo().defaultContent();
		return Constants.PASS;
	}

	public String selectCheckBox(String object, String data) {
		// TODO Auto-generated method stub
		

		List<WebElement> cb=getElements(object);
		boolean enable_value=cb.get(Integer.parseInt(data)).isSelected();
		if(!(enable_value))
		{
			cb.get(Integer.parseInt(data)).click();
		}
		
		return Constants.PASS;
		
		
	}
	public String radioSelect(String object, String data) {
		// TODO Auto-generated method stub
		
		List<WebElement> rb=getElements(object);
		System.out.println(rb.size());
		int count=0;
		for(int i=0;i<rb.size();i++)
		{
			String actual=rb.get(i).getAttribute("value");
			if(actual.contains(data))
			{
				rb.get(i).click();
				count++;
				return Constants.PASS;
			}
		}
		if(count==0)
		{
			return Constants.FAIL;
		}
		
		return Constants.PASS;
	}
	
	
	
	public String verifyAttribute(String object, String data) {
		// TODO Auto-generated method stub
		test.log(LogStatus.INFO,"Verify the attribute "+prop.getProperty(object));
		WebElement e=getElement(object);
		String actualText=e.getAttribute("value");
		System.out.println(".....  :"+actualText+"    :"+data);
		if(actualText.equalsIgnoreCase(data))
		{
			return Constants.PASS;
		}
		else
		{
			return Constants.FAIL;
		}
	}
	public String FieldVerification(String object, String data) {
		// TODO Auto-generated method stub
		int count=0;
		test.log(LogStatus.INFO,"Verifying the User ID -"+prop.getProperty(object));
		List<WebElement> e=getElements(object);
		for(int i=0;i<e.size();i++)
		{
			String id=e.get(i).getText();
			System.out.println(id+"... count value :  "+count+" .....data is :  "+data);
			if(id.equals(data))
				count++;
		}
		if(count!=0)
		{
			return Constants.PASS;
		}
		else
		{
			return Constants.FAIL;
		}
	}
	
	
	public String fileUpload(String object, String data) {
		// TODO Auto-generated method stub
		 StringSelection sel = new StringSelection(System.getProperty("user.dir")+data);
		 Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel,null);
		 System.out.println(".....Path of the file is :"+System.getProperty("user.dir")+data);
		 getElement(object).click();
		// Create object of Robot class
		 Robot robot = null;
		 
		 try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		      

		 try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 
		// Press Enter
		 robot.keyPress(KeyEvent.VK_ENTER);
		 
		// Release Enter
		 robot.keyRelease(KeyEvent.VK_ENTER);
		 
		// Press CTRL+V
		 robot.keyPress(KeyEvent.VK_CONTROL);
		 robot.keyPress(KeyEvent.VK_V);
		 
		// Release CTRL+V
		 robot.keyRelease(KeyEvent.VK_CONTROL);
		 robot.keyRelease(KeyEvent.VK_V);
		 
		 try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
	    // Press Enter 
		 robot.keyPress(KeyEvent.VK_ENTER);
		 robot.keyRelease(KeyEvent.VK_ENTER);
		 
		 
		return Constants.PASS;
	}

	public String alertText(String expectedText) {
		Alert alert=driver.switchTo().alert();
		String actualText=alert.getText();
		alert.accept();
		if(actualText.contains(expectedText))
		{
			return Constants.PASS;
		}
		else
		{
			return Constants.FAIL;
		}
	}

	public String verifyTextExactly(String object, String data) {
		WebElement e=getElement(object);
		String actualText=e.getText();
		System.out.println(".....  :"+actualText+"    :"+data);
		//test.log(LogStatus.INFO, ".....  :"+actualText+"    :"+expectedText);
		if(actualText.equals(data))
		{		
			return Constants.PASS;
		}
		else
		{
			return Constants.FAIL;
		}
	}
	
	public String elementEnabled(String object, String data) {
		WebElement e=getElement(object);
		boolean status=e.isEnabled();
		if(status)
		{
			return Constants.PASS;
		}
		else
		{
			return Constants.FAIL;
		}
						
	}
	
	public String elementDisabled(String object, String data) {
		WebElement e=getElement(object);
		boolean status=e.isEnabled();
		if(status)
		{
			return Constants.FAIL;
		}
		else
		{
			return Constants.PASS;
		
		}
	}
	
	
	
	public String verifyLength(String object, String data) {
		WebElement e=getElement(object);
		String actualText=e.getAttribute("maxlength");
		System.out.println(".....  :"+actualText+"    :"+data);
		//test.log(LogStatus.INFO, ".....  :"+actualText+"    :"+expectedText);
		if(actualText.equals(data))
		{		
			return Constants.PASS;
		}
		else
		{
			return Constants.FAIL;
		}
	}

	public String SelectInputValue(String object, String data) {
		int count=0;
		test.log(LogStatus.INFO,"Verifying the Day -"+prop.getProperty(object));
		List<WebElement> e=getElements(object);
		for(int i=0;i<e.size();i++)
		{
			String id=e.get(i).getText();
			System.out.println(id+"... count value :  "+count+" .....data is :  "+data);
			if(id.equals(data))
			{
				count++;
				e.get(i).click();
				break;
			}
		}
		if(count!=0)
		{
			return Constants.PASS;
		}
		else
		{
			return Constants.FAIL;
		}
	}

	public String FieldSelectionInTheRow(String object, String data) {
		int count=0;
		String name_edit[]=data.split("-");
		System.out.println("Split values are : "+name_edit[0]+"........"+name_edit[1]);
		test.log(LogStatus.INFO,"Fild selection in the row -"+prop.getProperty(object));
		System.out.println("Object is :"+prop.getProperty(object)+"/td["+name_edit[1]+"]");
		List<WebElement> e=driver.findElements(By.xpath(prop.getProperty(object)+"/td["+name_edit[1]+"]"));
		for(int i=0;i<e.size();i++)
		{
			String id=e.get(i).getText();
			System.out.println(id+"... count value :  "+count+" .....data is :  "+data);
			if(id.equals(name_edit[0]))
			{
				count++;
				System.out.println("Object is :"+prop.getProperty(object)+"["+(i+1)+"]/td["+name_edit[2]+"]/a");
				WebElement e1=driver.findElement(By.xpath(prop.getProperty(object)+"["+(i+1)+"]/td["+name_edit[2]+"]/a"));
				e1.click();
				break;
			}
		}
		if(count!=0)
		{
			return Constants.PASS;
		}
		else
		{
			return Constants.FAIL;
		}
	}

	public String FieldVerificationInTheRow(String object, String data) {
		int count=0;
		String name_edit[]=data.split("-");
		System.out.println("Split values are : "+name_edit[0]+"........"+name_edit[1]);
		test.log(LogStatus.INFO,"Fild selection in the row -"+prop.getProperty(object));
		System.out.println("Object is :"+prop.getProperty(object)+"/td["+name_edit[1]+"]");
		List<WebElement> e=driver.findElements(By.xpath(prop.getProperty(object)+"/td["+name_edit[1]+"]"));
		for(int i=0;i<e.size();i++)
		{
			String id=e.get(i).getText();
			System.out.println(id+"... count value :  "+count+" .....data is :  "+data);
			if(id.equals(name_edit[0]))
			{
				
				System.out.println("Object is :"+prop.getProperty(object)+"["+(i+1)+"]/td["+name_edit[2]+"]");
				WebElement e1=driver.findElement(By.xpath(prop.getProperty(object)+"["+(i+1)+"]/td["+name_edit[2]+"]"));
				System.out.println("Assigned names are : "+e1.getText()+"   .......  "+name_edit[3]);
				if(e1.getText().contains(name_edit[3]))
				{
					count++;
					//return Constants.PASS;
				}
				else
				{
					//return Constants.FAIL;
				}
				
			}
			
		}
		if(count!=0)
		{
			return Constants.PASS;
		}
		else
		{
			return Constants.FAIL;
		}
	}

	public String fileUploadInput(String object, String data) {
		test.log(LogStatus.INFO,"Uploading the file --"+prop.getProperty(object));
		WebElement e=getElement(object);
		System.out.println("File path is : "+System.getProperty("user.dir")+data);
		e.sendKeys(System.getProperty("user.dir")+data);
		return Constants.PASS;
	}
	public String verifyData(String data) {
		// TODO Auto-generated method stub
		WebElement e = null;
		test.log(LogStatus.INFO,"Verifying the specified text , data is :"+data);		
		String object=".//*[text()='"+data+"']";
		try
		{
		e=driver.findElement(By.xpath(object));
		}
		catch(Exception ex)
		{
			reportFailure("Failure in element extraction--"+object);
			Assert.fail("Failure in extracting the locator --");
		}
		String actualText=e.getText();
		System.out.println(".....  :"+actualText+"    :"+data);
		//test.log(LogStatus.INFO, ".....  :"+actualText+"    :"+expectedText);
		if(actualText.equals(data))
		{		
			return Constants.PASS;
		}
		else
		{
			return Constants.FAIL;
		}
		
		
	}

	public String clickData(String data) {
		test.log(LogStatus.INFO,"Click on the specified text link :"+data);		
		String object=".//*[text()='"+data+"']";
		WebElement e=driver.findElement(By.xpath(object));
		e.click();
		return Constants.PASS;
	}

	public String clickCheckbox(String object) {
		test.log(LogStatus.INFO,"Click on the Checker :"+object);		
		System.out.println("Click the checkboxes");
		List<WebElement> e=driver.findElements(By.xpath(object));
	
		test.log(LogStatus.INFO,"Total checkboxes are :"+e.size());
		System.out.println("Total checkboxes are :"+e.size());
		
		for(int i=0;i<e.size();i++)
		{
			test.log(LogStatus.INFO,i+ "th checkbox is clicked:");	
			System.out.println(i+ "th checkbox is clicked:");	
			
			e.get(i).click();
		}
		
		
		
		return Constants.PASS;
	}
	
	public String FieldSelectionClickViewButton(String object, String data) {
		
		int count=0;
		String name_edit[]=data.split("-");
		System.out.println("Split values are : "+name_edit[0]+"........"+name_edit[1]);
		test.log(LogStatus.INFO,"Fild selection in the row -"+prop.getProperty(object));
		System.out.println("Object is :"+prop.getProperty(object)+"/td["+name_edit[1]+"]");
		List<WebElement> e=driver.findElements(By.xpath(prop.getProperty(object)+"/td["+name_edit[1]+"]"));
		for(int i=0;i<e.size();i++)
		{
			String id=e.get(i).getText();
			System.out.println(id+"... count value :  "+count+" .....data is :  "+data);
			if(id.equals(name_edit[0]))
			{
				count++;
				System.out.println("Object is :"+prop.getProperty(object)+"["+(i+1)+"]/td["+name_edit[2]+"]/a[1]");
				WebElement e1=driver.findElement(By.xpath(prop.getProperty(object)+"["+(i+1)+"]/td["+name_edit[2]+"]/a[1]"));
				e1.click();
				break;
			}
		}
		if(count!=0)
		{
			return Constants.PASS;
		}
		else
		{
			return Constants.FAIL;
		}
		
		
	}

	public String SelectDatabase(String object,String data) throws SQLException 
	{
		
			String dbURL = "jdbc:sqlserver://192.168.33.33;integratedSecurity=true;";
			String user = "asdfasdfasdf";
			String pass = "";
			String query_field[]=data.split("-");
			System.out.println("Field Name :"+query_field[0]);
			System.out.println("Database value :"+query_field[1]);
						
			
			String query="SELECT "+query_field[0]+"  FROM table where field='"+object+"'";
			
			//String query_field[]=query.split("-");
			//System.out.println("Query is :"+query_field[0]);
			//System.out.println("Field is :"+query_field[1]);
			Connection conn = DriverManager.getConnection(dbURL,user,pass);
			if (conn != null) {
				System.out.println("Connected");
			}
			else
			{
				System.out.println("Not Connected");
			}
			Statement st=conn.createStatement();
			
			
			ResultSet rs=st.executeQuery(query);
			String abc="";
			while(rs.next())
			{
				abc=rs.getString(1);
				System.out.println(rs.getString(1));
				test.log(LogStatus.INFO,"The value in the data base is: "+rs.getString(1)+" : "+query_field[1]);		
			}
			System.out.println(abc+"   "+query_field[1]);
			if(abc.equalsIgnoreCase(query_field[1]))
			{	
					test.log(LogStatus.PASS,"Testcase Passed");
					return Constants.PASS;
			}
			else
			{
					test.log(LogStatus.FAIL,"Testcase Failed");
					return Constants.FAIL;
			}
	}
	
}
