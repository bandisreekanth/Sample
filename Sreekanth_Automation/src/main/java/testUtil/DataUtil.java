package testUtil;

import java.util.Hashtable;

public class DataUtil {

	public static Object[][] getData(Xls_Reader xls,String testName)
	{
		//  getData() reads the data and puts it  in 2D array
		//	Xls_Reader xls=new Xls_Reader(System.getProperty("user.dir")+"//Resources//SuiteB.xlsx");
		//	String testName="LoginTest";

		int rowStart=1;
		while(!xls.getCellData("Data", 0, rowStart).trim().toLowerCase().equals(testName.toLowerCase()) )
		{
			rowStart++;
		}
		
		int colStartRowNum=rowStart+1;
		int dataStartRowNum=rowStart+2;
		int rows=0;
		
		// Total rows of data in test
		while(!xls.getCellData("Data", 0, dataStartRowNum+rows).trim().toLowerCase().equals(""))
		{
			rows++;
		}
		
		// Total cols of data in the test case
		int col=0;
		while(!xls.getCellData("Data", col, colStartRowNum).trim().toLowerCase().equals(""))
		{
			col++;
		}
		System.out.println("Total Number of cols in "+testName+" is : "+col);

		//Read data
		int dataRow=0;
		Hashtable<String,String> table=null;
		Object [][] data=new Object[rows][1];
		for(int rNum=dataStartRowNum;rNum<dataStartRowNum+rows;rNum++)
		{
			table=new Hashtable<String,String>();
			for(int cNum=0;cNum<col;cNum++)
			{
				String key =xls.getCellData("Data", cNum,colStartRowNum);
				String value=(xls.getCellData("Data", cNum, rNum));
				//System.out.println("Hash Table Key is : "+key+" Value is : "+value);

				table.put(key,value);
			}
			data[dataRow][0]=table;
			//00 01 02 03...
			//10 11 12 12....
			dataRow++;
		}
		
		return data;
	}

	// true : N
	// false :  Y

	public static boolean isSkip(Xls_Reader xls,String testName)
	{
		int rows=xls.getRowCount(Constants.TESTCASES_SHEET);
		for(int rNum=2;rNum<=rows;rNum++)
		{
			String tcid=xls.getCellData(Constants.TESTCASES_SHEET,Constants.TCID_COL,rNum);
			if(tcid.equals(testName))
			{
				String runmode=xls.getCellData(Constants.TESTCASES_SHEET,Constants.RUN_MODE,rNum);
				if(runmode.equals("Y"))
				{
					return false;
				}
				else
				{
					return true;
				}
			}
		}
		return true;//default
	}
}


