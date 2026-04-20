// Write a java program to create login form with user id, password, ok button and cancel button.
// Handle key events such that pressing ‘I’ performs login and pressing ‘C’ clears text boxes and
// puts focus on user id, text box. Assume user table having fields Uid and Password in the
// database named account.

package lab2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;

public class LoginForm extends JFrame {

    JTextField txtUid;
    JPasswordField txtPassword;
    JButton btnOk, btnCancel;

    static final String URL = "jdbc:mysql://localhost:3306/account";
    static final String USER = "root";
    static final String PASSWORD = "Nishant123";

    public LoginForm() {

        setTitle("Login Form");
        setSize(350, 200);
        setLayout(new GridLayout(3, 2, 10, 10));

        JLabel lblUid = new JLabel("User ID:");
        JLabel lblPassword = new JLabel("Password:");

        txtUid = new JTextField();
        txtPassword = new JPasswordField();

        btnOk = new JButton("OK");
        btnCancel = new JButton("Cancel");

        add(lblUid);
        add(txtUid);
        add(lblPassword);
        add(txtPassword);
        add(btnOk);
        add(btnCancel);

        /* ---------- BUTTON EVENTS ---------- */

        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        /* ---------- KEY EVENTS ---------- */

        KeyAdapter keyHandler = new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == 'I' || e.getKeyChar() == 'i') {
                    login();
                }
                if (e.getKeyChar() == 'C' || e.getKeyChar() == 'c') {
                    clearFields();
                }
            }
        };

        txtUid.addKeyListener(keyHandler);
        txtPassword.addKeyListener(keyHandler);
        btnOk.addKeyListener(keyHandler);
        btnCancel.addKeyListener(keyHandler);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /* ---------- LOGIN ---------- */

    void login() {
        String uid = txtUid.getText().trim();
        String pass = new String(txtPassword.getPassword()).trim();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

            String sql = "SELECT * FROM user WHERE Uid=? AND Password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, uid);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login Successful");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid User ID or Password");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /* ---------- CLEAR ---------- */

    void clearFields() {
        txtUid.setText("");
        txtPassword.setText("");
        txtUid.requestFocus();
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}
