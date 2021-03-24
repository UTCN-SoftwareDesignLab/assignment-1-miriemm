package repository.user;

import model.User;
import model.validation.Notification;

import java.util.List;

public interface UserRepository {

    List<User> findAll();

   // Notification<User> findById(Long id);

    Notification<User> findByUsernameAndPassword(String username, String password);

    Notification<User> findByUsername(String username);

    boolean save(User user);

    boolean update(User user);

    boolean removeUser(Long id);

    void removeAll();

}
