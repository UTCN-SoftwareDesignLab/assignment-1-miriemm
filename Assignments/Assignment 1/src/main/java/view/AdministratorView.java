package view;


import model.User;
import model.validation.Notification;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static javax.swing.BoxLayout.Y_AXIS;

public class AdministratorView extends JFrame {

    private JLabel title;
    private JLabel manageUsers;
    private JLabel lUserId;
    private JTextField txtUserId;
    private JLabel lUsername;
    private JTextField txUsername;
    private JLabel lPassword;
    private JTextField txPassword;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnGenerateReports;
    private JLabel labelForImage;
    private DefaultTableModel tableModel;
    private JTable table;
    private JScrollPane scrollPane;


    public AdministratorView() throws HeadlessException {
        setTitle("Administrator");
        setLocationRelativeTo(null);
        initializeFields();
        setLayout(null);
        setResizable(false);

        // bounds for window
        setBounds(100, 100, 600, 800);

        title.setBounds(110, 5, 250, 100);
        title.setFont(new Font("TimesRoman", Font.PLAIN, 25));

        manageUsers.setBounds(110, 35, 250, 100);
        manageUsers.setFont(new Font("TimesRoman", Font.PLAIN, 20));

        //label for user id
        lUserId.setBounds(20, -30, 200, 300);
        // text field for user id
        txtUserId.setBounds(85, 110, 250, 20);

        //label for username
        lUsername.setBounds(20, 5, 200, 300);
        // text field for username
        txUsername.setBounds(85, 145, 250, 20);

        // label for password
        lPassword.setBounds(20, 35, 200, 300);
        // text field for password
        txPassword.setBounds(85, 175, 250, 20);

        btnAdd.setBounds(45, 225, 60, 40);

        btnUpdate.setBounds(115, 225, 90, 40);

        btnDelete.setBounds(215, 225, 100, 40);

        btnGenerateReports.setBounds(330, 225, 100, 40);

        // image
        labelForImage.setBounds(270, -60, 300, 400);

        tableModel.addColumn("ID");
        tableModel.addColumn("Username");
        tableModel.addColumn("Password");

        table.setModel(tableModel);
        // table.setBounds(80,300,300,300);

        scrollPane.setBounds(50, 300, 350, 350);


        add(title);
        add(manageUsers);
        add(lUserId);
        add(txtUserId);
        add(lUsername);
        add(txUsername);
        add(lPassword);
        add(txPassword);
        add(btnAdd);
        add(btnUpdate);
        add(btnDelete);
        add(btnGenerateReports);
        add(labelForImage);
        add(scrollPane);


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initializeFields() {

        title = new JLabel("ADMINISTRATOR");
        manageUsers = new JLabel("MANAGE USERS");
        txtUserId = new JTextField(15);
        txUsername = new JTextField(15);
        txPassword = new JTextField(15);
        lUserId = new JLabel("User ID:");
        lUsername = new JLabel("Username:");
        lPassword = new JLabel("Password:");
        btnAdd = new JButton("ADD");
        btnUpdate = new JButton("UPDATE");
        btnDelete = new JButton("DELETE");
        btnGenerateReports = new JButton("REPORTS");
        labelForImage = new JLabel(new ImageIcon("D:\\Desktop\\ANUL 3 SEM 2\\SD\\Assignment 1/rsz_1admin.png"));
        tableModel = new DefaultTableModel();
        table = new JTable();
        scrollPane = new JScrollPane(table);

        txtUserId.setEditable(false);

        //make table readonly
        table.setDefaultEditor(Object.class, null);

        table.getSelectionModel().addListSelectionListener(new updateUserFields());

    }

    public String getUserId() {
        return txtUserId.getText();
    }

    public String getUsername() {
        return txUsername.getText();
    }

    public String getPassword() {
        return txPassword.getText();
    }


    public void populateUserTable(ArrayList<User> userList) {
        // clear table before populating it
        tableModel.setRowCount(0);

        for (User user : userList) {
            String[] userTableRow = new String[]{
                    user.getId().toString(),
                    user.getUsername(),
                    user.getPassword()
            };

            tableModel.addRow(userTableRow);
        }

        txtUserId.setText("");
        txUsername.setText("");
        txPassword.setText("");

    }

    public void setAddUserButtonListener(ActionListener addUserButtonListener) {
        btnAdd.addActionListener(addUserButtonListener);
    }

    public void setUpdateUserButtonListener(ActionListener updateUserButtonListener) {
        btnUpdate.addActionListener(updateUserButtonListener);
    }

    public void setDeleteUserButtonListener(ActionListener deleteUserButtonListener) {
        btnDelete.addActionListener(deleteUserButtonListener);
    }

    private class updateUserFields implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent event) {

            // if a row is selected
            if (table.getSelectedRow() > -1) {
                String id = table.getValueAt(table.getSelectedRow(), 0).toString();
                String username = table.getValueAt(table.getSelectedRow(), 1).toString();
                String password = table.getValueAt(table.getSelectedRow(), 2).toString();

                txtUserId.setText(id);
                txUsername.setText(username);
                txPassword.setText(password);
            }
        }
    }

    public void setVisible() {
        this.setVisible(true);
    }

}
