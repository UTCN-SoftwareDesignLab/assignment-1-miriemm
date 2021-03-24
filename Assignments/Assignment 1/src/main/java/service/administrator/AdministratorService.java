package service.administrator;

import model.User;
import model.validation.Notification;

public interface AdministratorService {

    Notification<User> createEmployeeNotification(String username, String password);


}
