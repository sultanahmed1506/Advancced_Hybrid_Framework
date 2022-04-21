package Tests;

import org.testng.annotations.Test;
import reusableComponents.DB_Operations;

import java.util.HashMap;

public class DbTest {

    DB_Operations dbOps = new DB_Operations();

    @Test(priority = 2, description = "Database validation...")
    public void dbTest(){
        //verify DB
        //String sql = "SELECT * FROM `tasks` where name = '"+testData.get("TaskName")+"'";
        //DB_Operations dbOperationsUtil = new DB_Operations();
        String sql = "select * from account_master where ACCOUNT_KEY like '%79070674%'";
        HashMap<String, String> dbData = dbOps.getSqlResultInMap(sql);
        String data1 = dbData.get("CUSTODIAN_ACCOUNT_NUMBER");
        System.out.println("Data 1 :  "+data1);
        //assertEqualsString_custom("taskFailureName", TaskName, "DB_Task_Name");
    }

}
