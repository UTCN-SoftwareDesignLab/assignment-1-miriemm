package repository.account;

import model.Account;
import model.Client;
import model.validation.Notification;
import repository.EntityNotFoundException;

import java.util.List;


public interface AccountRepository {

    List<Account> findAll();

    Account findById(Long id) ;

    Notification<Account> findByClientId(Long clientId);

    boolean save(Account account);

    boolean updateAccount(Account account);

    boolean deleteAccount(Long id);

    void removeAll();

}
