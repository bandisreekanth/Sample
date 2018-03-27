package testKeywords;

import testUtil.Constants;
import testUtil.Xls_Reader;

import java.sql.SQLException;
import java.util.Hashtable;

import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Keyword {
	ExtentTest test;// Using this in keyword class to log here as well
	AppKeywords gk;
	public Keyword(ExtentTest test) {
		this.test=test;
	}
	public void executeKeywords(String testUnderExecution, Xls_Reader xls, Hashtable<String, String> data2){
		gk=new AppKeywords(test);
		int rowCount=xls.getRowCount(Constants.KEYWORDS_SHEET);

		//gk.reportFailure(failureMessage);
		System.out.println("Rows Count is :"+rowCount);
		
		for(int rowStart=2;rowStart<=rowCount;rowStart++){
			
			String TCID=xls.getCellData(Constants.KEYWORDS_SHEET,Constants.TCID_COL, rowStart);
			if(TCID.equalsIgnoreCase(testUnderExecution))
			{
				String keywords=xls.getCellData(Constants.KEYWORDS_SHEET,Constants.KEYWORD_COL, rowStart);
				String Object=xls.getCellData(Constants.KEYWORDS_SHEET,Constants.OBJECT_COL, rowStart);
				String key1=xls.getCellData(Constants.KEYWORDS_SHEET,Constants.DATA_COL, rowStart);
				String data=data2.get(key1);
				System.out.println(key1);
				System.out.println(keywords+"--"+Object+"--"+data);
				System.out.println("Printing the data :"+data2.get(key1));

				test.log(LogStatus.INFO,TCID+"--"+keywords+"--"+Object+"--"+data );// Replacing syso with test.log();
				try 
				{
					Thread.sleep(1000);				
				} 
				catch (InterruptedException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String result="PASS";// Every keyword would have a result//Empty string
				String result1="PASS";
				if(keywords.equals("openBrowser"))
				{
					System.out.println("In open browser");
					result=gk.openBrowser(data);
				}
				else if(keywords.equals("navigate"))
				{
					System.out.println("Navigate to Url");
					result=gk.navigate(Object);
				}
				else if (keywords.equals("click"))
				{
					result=gk.click(Object);
				}
				else if (keywords.equals("clear"))
				{
					result=gk.clear(Object);
				}
				else if (keywords.equals("title"))
				{
					result=gk.title(data);
				}
				else if (keywords.equals("input"))
				{
					result=gk.input(Object, data);
				}
				else if (keywords.equals("verifyAttribute"))
				{
					result=gk.verifyAttribute(Object, data);
				}


				else if (keywords.equals("inputCaptcha"))
				{
					result=gk.inputCaptcha(Object);
				}
				else if (keywords.equals("waiting"))
				{
					result=gk.waiting();
				}
				
				else if (keywords.equals("alertHandling"))
				{
					result=gk.alertHandling();
				}
				
				else if (keywords.equals("alertText"))
				{
					result=gk.alertText(data);
				}
				else if (keywords.equals("dropDown"))
				{
					result=gk.dropDown(Object, data);
				}
				else if (keywords.equals("dropDownValue"))
				{
					result=gk.dropDownValue(Object, data);
				}
				else if (keywords.equals("dropDownCount"))
				{
					result=gk.dropDownCount(Object, data);
				}
				else if (keywords.equals("dropDownCount"))
				{
					result=gk.dropDownVerify(Object, data);
				}
				
				else if (keywords.equals("elementEnabled"))
				{
					result=gk.elementEnabled(Object, data);
				}
				else if (keywords.equals("elementDisabled"))
				{
					result=gk.elementDisabled(Object, data);
				}

				
				
				else if(keywords.equals("closeBrowser"))
				{
					System.out.println("Close the Browser");
					result=gk.closeBrowser();
				}
				else if(keywords.equals("verifyText"))
				{
					result=gk.verifyText(Object, data);
				}
				else if(keywords.equals("verifyLength"))
				{
					result=gk.verifyLength(Object, data);
				}
				else if(keywords.equals("verifyTextExactly"))
				{
					result=gk.verifyTextExactly(Object, data);
				}
				else if(keywords.equals("handleMouseOver"))
				{
					result=gk.handleMouseOver(Object);
				}
				else if(keywords.equals("switchToFrame"))
				{
					result=gk.switchToFrame();
				}
				else if(keywords.equals("switchToDefaultFrame"))
				{
					result=gk.switchToDefaultFrame();
				}
				else if(keywords.equals("selectCheckBox"))
				{
					result=gk.selectCheckBox(Object,data);
				}
				else if(keywords.equals("radioSelect"))
				{
					result=gk.radioSelect(Object,data);
				}

				else if(keywords.equals("printScreenShot"))
				{
					result=gk.printScreenShot();
				}
				else if(keywords.equals("fileUpload"))
				{
					result=gk.fileUpload(Object,data);
				}
				else if(keywords.equals("fileUploadInput"))
				{
					result=gk.fileUploadInput(Object,data);
				}
				else if(keywords.equals("FieldVerification"))
				{
					result=gk.FieldVerification(Object,data);
				}
				else if(keywords.equals("SelectInputValue"))
				{
					result=gk.SelectInputValue(Object,data);
				}
				else if(keywords.equals("FieldSelectionInTheRow"))
				{
					result=gk.FieldSelectionInTheRow(Object,data);
				}
				else if(keywords.equals("FieldVerificationInTheRow"))
				{
					result=gk.FieldVerificationInTheRow(Object,data);
				}
				else if(keywords.equals("FieldSelectionClickViewButton"))
				{
					result=gk.FieldSelectionClickViewButton(Object,data);
				}
				else if(keywords.equals("verifyData"))
				{
					result=gk.verifyData(data);
				}	
				else if(keywords.equals("clickData"))
				{
					result=gk.clickData(data);
				}
				else if(keywords.equals("clickCheckbox"))
				{
					result=gk.clickCheckbox(Object);
				}
				
				else if(keywords.equals("SelectDatabase"))
				{
						try {
							result1=gk.SelectDatabase(Object,data);
														
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
											
				}
				
				else if (keywords.equals("scroll"))
				{
					result=gk.scroll(Object);
				}
				
				
						
				
				System.out.println("The result is .."+result);

				//Central place reporting failure, If any keywords returns fail then perform below function
				if(!result.equals(Constants.PASS))
				{
					gk.reportFailure("FAIL");//Goes to the reports
					Assert.fail("FAIL"); // Fails the test case
				}
				
				if(!result1.equals(Constants.PASS))
				{
					gk.reportFailure1("FAIL");
					Assert.fail("FAIL"); // Fails the test case
				}
			}
		}
	}
	public AppKeywords getGenericKeywords(){
		return gk;

	}


}
