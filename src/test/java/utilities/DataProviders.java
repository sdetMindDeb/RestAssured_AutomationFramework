package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name="Data")
    public String[][] getAllData() throws IOException
    {
        String path=System.getProperty("user.dir")+"//testData//TestData_RA_Automation.xlsx";
        excelUtility x1=new excelUtility(path);

        int rownum=x1.getRowCount("Sheet1");
        int colnum=x1.getCellCount("Sheet1",1);

        String apiData[][]=new String[rownum][colnum];

        for(int i=1;i<rownum;i++)
        {
            for(int j=0;j<colnum;j++)
            {
                apiData[i-1][j]=x1.getCellData("Sheet1",i,j);
            }
        }
        return apiData;
    }

    @DataProvider(name="UserNames")
    public String[] getUserNames() throws IOException
    {
        String path=System.getProperty("user.dir")+"//testData//TestData_RA_Automation.xlsx";
        excelUtility x1=new excelUtility(path);

        int rownum=x1.getRowCount("Sheet1");

        String apiData[]=new String[rownum];

        for(int i=1;i<rownum;i++)
        {
                apiData[i-1]=x1.getCellData("Sheet1",i,1);
        }
        return apiData;
    }
}
