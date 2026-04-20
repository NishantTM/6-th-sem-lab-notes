// Consider db_college and tbl_student as database name and table respectively where a tbl_student
// has columns name, faculty and batch. Write console application with CREATE, READ, UPDATE and
// DELETE queries only using prepared statement.
package lab2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class studentCrud {
    static String URL = "jdbc:mysql://localhost:3306/db_college";
    static String USER = "root";
    static String PASSWORD = "Nishant123";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            while (true) {
                System.out.println("\n --------Student Crud operation-----------");
                System.out.println("1. Insert Student");
                System.out.println("2. View Students");
                System.out.println("3. Update Student Faculty");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");

                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    // create
                    case 1:
                        String insertSQL = "INSERT INTO tbl_student (name, faculty, batch) VALUES (?,?,?)";
                        PreparedStatement psInsert = connection.prepareStatement(insertSQL);

                        System.out.println("Enter Name:");
                        psInsert.setString(1, sc.nextLine().trim());

                        System.out.println("Enter Faculty: ");
                        psInsert.setString(2, sc.nextLine().trim());

                        System.out.println("Enter Batch: ");
                        psInsert.setInt(3, sc.nextInt());
                        sc.nextLine();

                        psInsert.executeUpdate();
                        System.out.println("Student inserted succesfully");
                        break;

                    // Read
                    case 2:
                        String selectSQL = "SELECT * FROM tbl_student";
                        PreparedStatement psSelect = connection.prepareStatement(selectSQL);
                        ResultSet rs = psSelect.executeQuery();

                        System.out.println("\nName\tFaculty\tBatch");
                        System.out.println("-------------------------");

                        while (rs.next()) {
                            System.out.println(
                                    rs.getString("name") + "\t" +
                                            rs.getString("faculty") + "\t" +
                                            rs.getInt("batch"));
                        }

                        break;

                    // Update
                    case 3:
                        String updateQuery = "UPDATE tbl_student SET faculty = ? WHERE name = ?";
                        PreparedStatement psUpdate = connection.prepareStatement(updateQuery);

                        System.out.print("Enter student name: ");
                        String uname = sc.nextLine().trim();

                        System.out.print("Enter new faculty: ");
                        String newFaculty = sc.nextLine().trim();

                        psUpdate.setString(1, newFaculty);
                        psUpdate.setString(2, uname);

                        int rows = psUpdate.executeUpdate();

                        if (rows > 0) {
                            System.out.println("Student updated successfully.");
                        } else {
                            System.out.println("No student found with that name.");
                        }
                        break;

                        //Delete query
                    case 4:
                        String deleteSQL = "DELETE FROM tbl_student WHERE name = ?";

                        PreparedStatement psDelete = connection.prepareStatement(deleteSQL);

                        System.out.println("Enter Student Name to delete:");
                        psDelete.setString(1, sc.nextLine().trim());
                        psDelete.executeUpdate();

                        int drows = psDelete.executeUpdate();
                        if (drows > 0) {
                            System.out.println("Student deleted successfully.");
                        } else {
                            System.out.println("No student found.");
                        }

                        break;

                    case 5:
                        connection.close();
                        sc.close();
                        System.out.println("Program terminated.");
                        return;

                    default:
                        System.out.println("Invalid choice ");
                        break;
                }

            }
        } catch (Exception e) {

        }

    }

}
