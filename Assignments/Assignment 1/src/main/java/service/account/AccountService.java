package service.account;

import model.Account;
import model.Client;
import model.validation.Notification;

import java.util.List;

public interface AccountService {

    Notification<Account> findByClientId(Long id);

    boolean saveAccount(Account account);

    boolean updateAccount(Account account);

    boolean deleteAccount(Long id);


}
