// Write a program to extract the user information such as Reg_no, Name, Address, Phone_no from the
// database and display in the console.

package lab2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DisplayUserInfo {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Step 1: Load JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish Connection
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/citizen",
                    "root",
                    "Nishant123"
            );

            // Step 3: Create Statement
            stmt = conn.createStatement();

            // Step 4: Execute Query
            String sql = "SELECT * FROM user_info";
            rs = stmt.executeQuery(sql);

            // Step 5: Process ResultSet
            System.out.println("User Information:");
            System.out.println("------------------------------------------------");
            while (rs.next()) {
                int regNo = rs.getInt("Reg_no");
                String name = rs.getString("Name");
                String address = rs.getString("Address");
                String phone = rs.getString("Phone_no");

                System.out.println("Reg_no: " + regNo);
                System.out.println("Name: " + name);
                System.out.println("Address: " + address);
                System.out.println("Phone_no: " + phone);
                System.out.println("------------------------------------------------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

