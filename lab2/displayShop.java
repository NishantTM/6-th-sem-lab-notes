// Write a program to insert three records into a table Item which is in the database shop and contains
// the columns ItemID, Name, UnitPrice, Units and Expiry Date.
package lab2;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;

public class displayShop extends JFrame implements ActionListener {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/shop";
        String username = "root";
        String password = "Nishant123";
        
        String sql = "INSERT INTO Item (ItemID, Name, UnitPrice, Units, ExpiryDate) VALUES (?, ?, ?, ?, ?)";

        try {
            // Load MySQL JDBC Driver
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            try {
                // Create connection
                Connection connection = DriverManager.getConnection(url, username, password);

                // Prepare statement
                PreparedStatement ps = connection.prepareStatement(sql);

                // Record 1
                ps.setInt(1, 101);
                ps.setString(2, "Noodles");
                ps.setDouble(3, 20.50);
                ps.setInt(4, 40);
                ps.setDate(5, java.sql.Date.valueOf("2026-01-14"));
                ps.executeUpdate();

                ps.setInt(1, 102);
                ps.setString(2, "Soap");
                ps.setDouble(3, 50.75);
                ps.setInt(4, 30);
                ps.setDate(5, java.sql.Date.valueOf("2025-12-20"));
                ps.executeUpdate();

                ps.setInt(1, 103);
                ps.setString(2, "Shampoo");
                ps.setDouble(3, 200.75);
                ps.setInt(4, 100);
                ps.setDate(5, java.sql.Date.valueOf("2026-01-01"));
                ps.executeUpdate();

                System.out.println("Three records inserted succesfully");

                //close connection
                ps.close();
                connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
    }

}
