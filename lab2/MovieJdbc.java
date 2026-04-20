// Assume a table MOVIE(id, title, genre). Now, using JDBC perform queries:
// a. Add any three records to the MOVIE table.
// b. Using a prepared statement, update the genre to “
package lab2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MovieJdbc {
    public static void main(String[] args) {

        Connection conn = null;

        try {
            // Load Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                // Connect to Database
                conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/citizen",
                        "root",
                        "Nishant123");

                System.out.println("Database connected succesfully");
                String insertQuery = "INSERT INTO MOVIE(id, title, genre) VALUES (?,?,?)";
                PreparedStatement insertstmt = conn.prepareStatement(insertQuery);

                // Movie 1
                insertstmt.setInt(1, 4);
                insertstmt.setString(2, "Jatra");
                insertstmt.setString(3, "Drama");
                insertstmt.executeUpdate();

                // movie 2
                insertstmt.setInt(1, 5);
                insertstmt.setString(2, "Pushpa");
                insertstmt.setString(3, "Action and comedy");
                insertstmt.executeUpdate();

                // movie 3
                insertstmt.setInt(1, 6);
                insertstmt.setString(2, "Fast and Furious");
                insertstmt.setString(3, "Actions");
                insertstmt.executeUpdate();

                System.out.println("Record inserted succesfully");

                // Step 3b: Update genre to "Comedy" where title = "Jatra"
                String updateQury = "UPDATE MOVIE SET genre = ? WHERE title = ?";
                PreparedStatement updatestmt = conn.prepareStatement(updateQury);

                updatestmt.setString(1, "Comedy");
                updatestmt.setString(2, "Jatra");

                int rowUpdated = updatestmt.executeUpdate();
                System.out.println(rowUpdated + " record(s) updated succesfully. ");

                insertstmt.close();
                updatestmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
