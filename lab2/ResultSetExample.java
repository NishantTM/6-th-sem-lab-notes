// 1. ResultSet Example with insertion, deletion, update and retrieve
package lab2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner; 

public class ResultSetExample {
    public static Connection getConnection() throws Exception {
        String driver = "com.mysql.cj.jdbc.Driver";
        String databaseURL = "jdbc:mysql://localhost:3306/Nesfield";
        String username = "root";
        String password = "Nishant123";
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(databaseURL, username, password);
        System.out.println("Database connected");

        return conn;
    }

    // Insert record
    private static void insertRecord(Connection connection, int roll_no, String name, String email, String address)
            throws Exception {
        String query = "INSERT INTO nesfield_student (roll_no, name, email, address) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, roll_no);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, email);
        preparedStatement.setString(4, address);

        int rows = preparedStatement.executeUpdate();
        if (rows > 0) {
            System.out.println("Record inserted succesfully");
            
        }
    }

    // Update Record
    private static void updateRecord(Connection connection, int roll_no, String name) {
        try {
            String query = "UPDATE nesfield_student SET name = ? WHERE roll_no = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, roll_no);
            preparedStatement.executeUpdate();
            System.out.println("Record updated succesfully");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    // Retrieve Records
    private static void retrieveRecords(Connection connection) {
        try {
            java.sql.Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM nesfield_student");
            System.out.println("Record in the database");
            while (resultSet.next()) {
                System.out.println("Roll NO :" + resultSet.getInt("roll_no"));
                System.out.println("Name :" + resultSet.getString("name"));
                System.out.println("Email :" + resultSet.getString("email"));
                System.out.println("Address :" + resultSet.getString("address"));
                System.out.println("---------------------------");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete Records
    private static void deleteRecord(Connection connection, int roll_no) {
        try {
            String query = "DELETE FROM nesfield_student WHERE roll_no = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, roll_no);
            preparedStatement.executeUpdate();
            System.out.println("Record deleted succesfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            Connection connection = getConnection();
            System.out.println("Select an operation");
            System.out.println("1. Insert");
            System.out.println("2. Update");
            System.out.println("3. Retrieve");
            System.out.println("4. Delete");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter Roll NO:");
                    int roll_no = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter Name");
                    String name = scanner.nextLine();
                    System.out.println("Enter Email");
                    String email = scanner.nextLine();
                    System.out.println("Enter Address");
                    String address = scanner.nextLine();
                    insertRecord(connection, roll_no, name, email, address);
                    break;

                case 2:
                    System.out.println("Enter Roll No to Update:");
                    int rollUpdate = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter new Name:");
                    String nameName = scanner.nextLine();
                    updateRecord(connection, rollUpdate, nameName);
                    break;

                case 3:
                    retrieveRecords(connection);
                    break;

                case 4:
                    System.out.println("Enter Roll no to delete: ");
                    int rollDelete = scanner.nextInt();
                    deleteRecord(connection, rollDelete);
                    break;

                default:
                    System.out.println("Invalid Choice");
                    break;
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
