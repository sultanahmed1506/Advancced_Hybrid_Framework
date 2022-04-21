package reusableComponents;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class DB_Operations {
	
	static Connection con = null;
    // Statement object
    private static Statement stmt;
    //Database Username
    static String DB_USER = PropertiesOperations.getPropertyValueByKey("dbuser");
    // Database Password
    static String DB_PASSWORD = PropertiesOperations.getPropertyValueByKey("dbpass");
    public static String DB_URL = "jdbc:oracle:thin:" + DB_USER + "/" +DB_PASSWORD + "@//exapoend-scan.onefiserv.net:2501/DEVL_dcrecnrc_AppService";

    public synchronized HashMap<String, String> getSqlResultInMap(String sql) {
        HashMap<String, String> data_map = new HashMap<>();

        try{
            String dbClass = "oracle.jdbc.driver.OracleDriver";
            Class.forName(dbClass);
            con = DriverManager.getConnection(DB_URL);

            // Statement object to send the SQL statement to the Database
            stmt = con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();

            while (rs.next()) {
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    data_map.put(md.getColumnName(i), rs.getString(i));
                    //System.out.println("Column Name : "+md.getColumnName(i));
                    //System.out.println("Data : "+rs.getString(i));
                }
            }
            System.out.println(data_map);
            con.close();
        }catch (SQLException|ClassNotFoundException ex)
        {
            System.err.println("Failed to connect to Oracle Database/Check properties file");
            ex.printStackTrace();
        }
        return data_map;
    }
}
