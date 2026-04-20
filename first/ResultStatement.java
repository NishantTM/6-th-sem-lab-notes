package first;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultStatement {

    public static void main(String[] args) {
        metaDataDemo();
        
    }

    public static Connection getConnection(){
     try {
        String driver = "com.mysql.cj.jdbc.Driver";
        String databaseUrl = "jdbc:mysql://localhost:3306/Nesfield";
        String username = "root";
        String password = "Nishant123";
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(databaseUrl, username, password);
        System.out.println("Datbase connected");
        return connection;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error:"+e);
        }
           return null;

    }
    private static void metaDataDemo() {
       
        Connection conn = getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery("SELECT * FROM nesfield_student");
            ResultSetMetaData RSMD = result.getMetaData();

            System.out.println("Number of columns:"+ RSMD.getColumnCount());
            System.out.println("First column type:"+ RSMD.getColumnType(1));
            System.out.println("First column type:"+ RSMD.getColumnTypeName(1));
        } catch (Exception e) {
            System.out.println("Error:"+ e);
           
        } 
    }

 

    
}