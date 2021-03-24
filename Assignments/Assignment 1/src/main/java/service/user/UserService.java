package service.user;

import model.User;
import model.validation.Notification;

import java.util.List;

public interface UserService {

   Notification<User> findByUsername(String username);

    boolean save(User user);

    boolean update(User user);

    boolean removeUser(Long id);

    List<User> getAllUsers();

}
