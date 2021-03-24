package service.user;

import model.Role;
import model.User;
import model.validation.Notification;
import repository.security.RightsRolesRepository;
import repository.user.UserRepository;

import java.util.Collections;
import java.util.List;

import static database.Constants.Roles.EMPLOYEE;

public class UserServiceMySQL implements  UserService {

    private UserRepository userRepository;
    private RightsRolesRepository rightsRolesRepository;

    public UserServiceMySQL(UserRepository userRepository, RightsRolesRepository rightsRolesRepository) {
        this.userRepository = userRepository;
        this.rightsRolesRepository = rightsRolesRepository;
    }

    @Override
    public Notification<User>findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean save(User user) {
        Role employeeRole = rightsRolesRepository.findRoleByTitle(EMPLOYEE);
        user.setRoles(Collections.singletonList(employeeRole));
        return userRepository.save(user);
    }

    @Override
    public boolean update(User user) {
        return userRepository.update(user);
    }

    @Override
    public boolean removeUser(Long id) {
        return userRepository.removeUser(id);
    }




}
