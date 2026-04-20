// Write a java program to design an GUI application that reads teacher information like as tchID,
// tchName, tchAddress, tchPhone and store into a database called “citizen” when user clicks on a
// button(login).

package lab2;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;

public class TeacherForm extends JFrame implements ActionListener, java.awt.event.ActionListener {

    JTextField txtID, txtName, txtPhone;
    JTextArea txtAddress;
    JButton btnLogin;
    Connection conn;

    TeacherForm() throws SQLException {
        setTitle("Teaher Information");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Layoutt 
        setLayout(new GridLayout(5, 2, 10, 10));

        // Components
        add(new JLabel("Teacher ID"));
        txtID = new JTextField();
        add(txtID);


        add(new JLabel("Teacher Name"));
        txtName = new JTextField();
        add(txtName);

        add(new JLabel("Teacher Address"));
        txtAddress = new JTextArea(3, 20);
        add(txtAddress);

        add(new JLabel("Teacher Phone"));
        txtPhone = new JTextField();
        add(txtPhone);

        btnLogin = new JButton("Login");
        add(btnLogin);

        add(new JLabel());

        btnLogin.addActionListener(this);
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
            int id = Integer.parseInt(txtID.getText());
            String name = txtName.getText();
            String address = txtAddress.getText();
            String phone = txtPhone.getText();

            String query = "INSERT INTO teacher VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, phone);

            preparedStatement.execute();

            JOptionPane.showMessageDialog(this, "Teacher Record Inserted Succesfully");

            // clear fields
            txtID.setText("");
            txtName.setText("");
            txtAddress.setText("");
            txtPhone.setText("");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

     public static void main(String[] args) {
        try {
            new TeacherForm();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

     @Override
     public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
     }
}
