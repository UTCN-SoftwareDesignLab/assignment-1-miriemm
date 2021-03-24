package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import static javax.swing.BoxLayout.Y_AXIS;

public class LoginView extends JFrame {

    private JLabel title;
    private JTextField tfUsername;
    private JPasswordField tfPassword;
    private JLabel lUsername;
    private JLabel lPassword;
    private JButton btnLogin;
    private JCheckBox passwordVisibility;
    private JCheckBox checkbox1;
    private JCheckBox checkbox2;
    private JLabel labelForImage;


    public LoginView() throws  HeadlessException {
        setTitle("Login");
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(null);
        setResizable(false);


        // bounds for window
        setBounds(100, 100, 500, 500);

        title.setBounds(130,-70,200,300);
        title.setFont(new Font("TimesRoman", Font.PLAIN, 30));

        //label for username
        lUsername.setBounds(20, 5, 200, 300);
        // text field for username
        tfUsername.setBounds(85, 145, 250, 20);

        // label for password
        lPassword.setBounds(20, 35, 200, 300);
        // text field for password
        tfPassword.setBounds(85, 175, 250, 20);

        // checkbox
        passwordVisibility.setBounds(340, 175, 100, 20);

        checkbox1.setBounds(80, 200, 100, 20);
        checkbox2.setBounds(200, 200, 100, 20);

        // login button
        btnLogin.setBounds(125, 250, 100, 20);
        btnLogin.setBackground(Color.green);

        // image
        labelForImage.setBounds(180,150,300,400);


        add(title);
        add(lUsername);
        add(tfUsername);
        add(lPassword);
        add(tfPassword);
        add(passwordVisibility);
        add(checkbox1);
        add(checkbox2);
        add(btnLogin);
        add(labelForImage);



        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }

    private void initializeFields() {
        title = new JLabel("LOGIN FORM");
        tfUsername = new JTextField(20);
        tfPassword = new JPasswordField(20);
        lUsername = new JLabel("Username: ");
        lPassword = new JLabel("Password: ");
        btnLogin = new JButton("Login");
        passwordVisibility = new JCheckBox("Show");
        checkbox1 = new JCheckBox("Administrator");
        checkbox2 = new JCheckBox("Employee");

        labelForImage = new JLabel(new ImageIcon("D:\\Desktop\\ANUL 3 SEM 2\\SD\\Assignment 1/rsz_b.png"));



    }

    public String getUsername() {
        return tfUsername.getText();
    }

    public String getPassword() {
        return tfPassword.getText();
    }


    public JPasswordField getPass() {return tfPassword;}

    public JCheckBox getPasswordVisibility() { return passwordVisibility;}



    public void setLoginButtonListener(ActionListener loginButtonListener) {
        btnLogin.addActionListener(loginButtonListener);
    }


    public void setPasswordVisibilityListener(ActionListener passwordVisibilityListener) {
        passwordVisibility.addActionListener(passwordVisibilityListener);

    }


    public void setVisible() {
        this.setVisible(true);
    }

}
