package first;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class FirstDemo
{
    public static void main(String args[]) 
    {
        getData();
    }
    public static Connection getConnection() {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String databaseUrl = "jdbc:mysql://localhost:3306/Nesfield";
            String username = "root";
            String password = "Nishant123";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(databaseUrl, username, password);
            System.out.println("Database connected");
            return conn;
        } 
        catch (Exception e) 
        {
            System.out.println("Some error: " + e);
        }
        return null;
    }

    public static void getData() 
    {
        try 
        {
            Statement stmt = getConnection().createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM nesfield_student");
            while (result.next()) {
                System.out.println(result.getString("roll_no"));
                System.out.println(result.getString("name"));
                System.out.println(result.getString("email"));
                System.out.println(result.getString("address"));
            }
        } 
        catch (Exception e) 
        {
            System.out.println("Error: " + e);
        }
    }
}