package Tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.LoginPageObjects;
import pageObjects.ReconPageObjects;
import reusableComponents.ExcelOperations;
import testBase.ExtentFactory;
import testBase.TestBase;

import java.util.HashMap;

public class InvalidUserLoginTest {
    public class InValidUserLoginTests extends TestBase {
        LoginPageObjects loginPage = new LoginPageObjects();
        ReconPageObjects reconPage = new ReconPageObjects();
        ExcelOperations excel = new ExcelOperations("InValidUsers");


        @Test(dataProvider = "InValidUsers", description = "InValid username and password test")
        public void InvalidUserLoginTest(Object obj1) throws Throwable {
            HashMap<String, String> testData = (HashMap<String, String>) obj1;
            ExtentFactory.getInstance().getExtent().info("Test Data for this execution run is: "+ testData);

            loginPage.Dologin(testData.get("username"), testData.get("password"));
            loginPage.ErrorLoginMessageExist();
            //loginPage.getLoginPageTitle();
        }

        @DataProvider(name = "InValidUsers")
        public Object[][] testDataSupplier() throws Exception {
            Object[][] obj = new Object[excel.getRowCount()][1];
            for (int i = 1; i <= excel.getRowCount(); i++) {
                HashMap<String, String> testData = excel.getTestDataInMap(i);
                obj[i-1][0] = testData;
            }
            return obj;
        }

    }
}