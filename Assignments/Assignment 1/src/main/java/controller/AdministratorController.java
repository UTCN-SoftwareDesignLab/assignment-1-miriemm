package controller;

import model.User;
import model.validation.Notification;
import service.administrator.AdministratorService;
import service.user.UserService;
import view.AdministratorView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdministratorController {

    private final AdministratorView administratorView;
    private final UserService userService;

    public AdministratorController(AdministratorView administratorView, UserService userService) {
        this.administratorView = administratorView;
        this.userService = userService;
        administratorView.setAddUserButtonListener(new AddUserButtonListener());
        administratorView.setUpdateUserButtonListener(new UpdateUserButtonListener());
        administratorView.setDeleteUserButtonListener(new DeleteUserButtonListener());

        refreshUserTableView();

    }

    private class AddUserButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = administratorView.getUsername();
            String password = administratorView.getPassword();

            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(password);

            Notification<User> existingUser = userService.findByUsername(username);
            //if we didn't find an existing user with same username then add it to db
            if(existingUser.getFormattedErrors() == "User not found!") {
                JOptionPane.showMessageDialog(administratorView.getContentPane(), "User added succesfully!");
                userService.save(newUser);
                refreshUserTableView();

            }else {
                JOptionPane.showMessageDialog(administratorView.getContentPane(), "User already exists!");
            }
        }
    }

    private class UpdateUserButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String userId = administratorView.getUserId();
            String username = administratorView.getUsername();
            String password = administratorView.getPassword();

            User updatedUser = new User();
            updatedUser.setId(Long.parseLong(userId));
            updatedUser.setUsername(username);
            updatedUser.setPassword(password);


            if(userService.update(updatedUser)) {
                JOptionPane.showMessageDialog(administratorView.getContentPane(), "User updated succesfully!");

                refreshUserTableView();

            }else {
                JOptionPane.showMessageDialog(administratorView.getContentPane(), "Cannot update unexisting user!");
            }
        }
    }


    private class DeleteUserButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String userId = administratorView.getUserId();

            if(userService.removeUser(Long.parseLong(userId))) {
                JOptionPane.showMessageDialog(administratorView.getContentPane(), "User deleted succesfully!");

                refreshUserTableView();

            } else {
                JOptionPane.showMessageDialog(administratorView.getContentPane(), "Cannot delete unexisting user!");
            }
        }
    }


    private void refreshUserTableView() {
        ArrayList<User> userList = new ArrayList<User>(userService.getAllUsers());
        administratorView.populateUserTable(userList);
    }


}
