package first;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Getfromuser {
    public static void main(String[] args) {
        GetDataUser();
    }
    public static Connection getConnection(){
        
        try {
        String driver = "com.mysql.cj.jdbc.Driver";
        String databaseUrl = "jdbc:mysql://localhost:3306/Nesfield";
        String username = "root";
        String password = "Nishant123";
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(databaseUrl, username, password);
        System.out.println("Database Connected");
              return conn;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error:"+ e);
           
        }
        return null;

           
    }

    private static void GetDataUser() {
        try 
        {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter your roll number");
            int id = sc.nextInt();
            
            Statement statement = getConnection().createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM nesfield_student where roll_no = " +id);
            while (result.next()) 
            {
                System.out.println(result.getString("Roll_no"));
                System.out.println(result.getString("Name"));
                System.out.println(result.getString("Email"));
                System.out.println(result.getString("Address"));
            }
        } 
        catch (Exception e) 
        {
            System.out.println("Error: " + e);
        }   
    }  
}
