// You are hired by a reputed software company which is going to design an application for “Movie Rental
// System”. Your responsibility is to design a schema named MRS and create a table named Movie(id,
// Tille, Genre, Language, Length). Write a program to design a GUI form to take input for this table and
// insert the data into table after clicking the OK button.
package lab2;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class MoviewRentalform extends JFrame implements ActionListener {
    JTextField movieid, movieTitle, movieGenre, movieLanguage, movieLength;
    JButton okButton;

    Connection conn;

    MoviewRentalform() throws SQLException {
        setTitle("Movie Rental System");
        setSize(400, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridLayout(6, 2, 10, 20));
       

        // Components

        add(new JLabel("Movie ID:"));
        movieid = new JTextField();
        add(movieid);

        add(new JLabel("Movie Title:"));
        movieTitle = new JTextField();
        add(movieTitle);

        add(new JLabel("Movie Genre:"));
        movieGenre = new JTextField();
        add(movieGenre);

        add(new JLabel("Movie Language:"));
        movieLanguage = new JTextField();
        add(movieLanguage);

        add(new JLabel("Movie Length (minutes):"));
        movieLength = new JTextField();
        add(movieLength);

        okButton = new JButton("OK");
        add(okButton);

        add(new JLabel());

        okButton.addActionListener(this);

        connectDB();
        setVisible(true);

    }

    private void connectDB() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/citizen",
                    "root",
                    "Nishant123");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        try {
            int mid = Integer.parseInt(movieid.getText());
            String mtitle = movieTitle.getText();
            String mgenre = movieGenre.getText();
            String mlanguage = movieLanguage.getText();
            String mlength = movieLength.getText();

            String query = "INSERT INTO Movie VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, mid);
            preparedStatement.setString(2, mtitle);
            preparedStatement.setString(3, mgenre);
            preparedStatement.setString(4, mlanguage);
            preparedStatement.setString(5, mlength);

            preparedStatement.execute();

            JOptionPane.showMessageDialog(this, "Movie Record Inserted Succesfully");

            // clear fields
            movieid.setText("");
            movieTitle.setText("");
            movieGenre.setText("");
            movieLanguage.setText("");
            movieLength.setText("");

        } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    public static void main(String[] args) throws SQLException {
        new MoviewRentalform();
    }

}
