package first;
import java.sql.*;

public class DeletionQuery {
    public static void main(String[] args) {
        DeleteData(3);
    }
       public static Connection getConnection() {
      try {
         String driver = "com.mysql.cj.jdbc.Driver";
         String databaseUrl = "jdbc:mysql://localhost:3306/Nesfield";
         String username = "root";
         String password = "Nishant123";
         Class.forName(driver);
         Connection var4 = DriverManager.getConnection(databaseUrl, username, password);
         System.out.println("Database Connected");
         return var4;
      } catch (Exception var5) {
         System.err.println("Some error:" + String.valueOf(var5));
         return null;
      }
   }

    private static void DeleteData(int roll_no) {
        try {
            Statement statement = getConnection().createStatement();
            String query = "DELETE FROM nesfield_student WHERE roll_no = 3";
        int result = statement.executeUpdate(query);
        System.out.println(result +"row(s) Deleted data form the table");
        } catch (SQLException e) {
            System.err.println("Some error:"+e);
            
        }
    }
}
