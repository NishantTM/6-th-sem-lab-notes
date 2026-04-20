package first;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class UpdateStatement {
    public static void main(String[] args) {
        UpdateData();
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
    private static void UpdateData() {
        try {
            Connection connection = getConnection();
            String sql = "UPDATE nesfield_student SET address = ? WHERE roll_no = ?";
            PreparedStatement ps =  connection.prepareStatement(sql);

            ps.setString(1, "Hatiwan");
            ps.setInt(2,2);
            
            int rows = ps.executeUpdate();
            System.out.println(rows +"row(s) updated");

            // ps.setString(2, "Bajrabarahi");
            // ps.setInt(2,4);
            // ps.executeUpdate
            // System.out.println("Update successful");

            //NOw fetch the updated row
            String slectSql = "SELECT roll_no, name, email, address FROm nesfield_student WHERE roll_no = ?";
            PreparedStatement selectPS = connection.prepareStatement(slectSql);
            selectPS.setInt(1, 2);

            var rs = selectPS.executeQuery();
            while (rs.next()) {
            int rollNo = rs.getInt("roll_no");
            String name = rs.getString("name");
            String email = rs.getString("email");
            String address = rs.getString("address");

            System.out.println("Updated Row → roll_no: " + rollNo +
                               ", name: " + name +
                                 ", email: " + email +
                               ", address: " + address);
                
            }
            rs.close();
            selectPS.close();
            ps.close();
            connection.close();
        } 
        catch (SQLException e) {
            System.out.println("Error:"+ e);
           
        }
    }

    
}
