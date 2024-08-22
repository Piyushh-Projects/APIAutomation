package api.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name = "Data")
    public String [][]getAddData() throws IOException {

        String path = System.getProperty("user.dir")+"//testData//Userdata.xlsx";
        excelUtility excel = new excelUtility(path);

        int rowNum = excel.getRowCount("Sheet1");
        int colCount = excel.getCellCount("Sheet1",1);
        String[][] apiData = new String[rowNum][colCount];

        for (int i = 1; i <= rowNum; i++){
            for (int j = 0; j <= colCount; j++){
                apiData[i-1][j] = excel.getCellData("Sheet1",i,j);
            }
        }
        return apiData;
    }

    @DataProvider(name = "UserNames")
    public String [] getUserNames() throws IOException {

        String path = System.getProperty("user.dir")+"//Userdata.xlsx";
        excelUtility excel = new excelUtility(path);

        int rowNum = excel.getRowCount("Sheet1");
        String[] apiData = new String[rowNum];

        for (int i = 1; i <= rowNum; i++) {
            apiData[i-1] = excel.getCellData("Sheet1", i, 1);
        }
        return apiData;
    }
}
