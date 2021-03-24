package service.account;

import model.Account;
import model.Client;
import model.validation.Notification;
import repository.account.AccountRepository;

public class AccountServiceMySQL implements AccountService{

    private AccountRepository accountRepository;

    public AccountServiceMySQL(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public  Notification<Account> findByClientId(Long clientId) {
        return  accountRepository.findByClientId(clientId);
    }

    @Override
    public boolean saveAccount(Account account) { return accountRepository.save(account);}

    @Override
    public boolean updateAccount(Account account) { return accountRepository.updateAccount(account);}

    @Override
    public boolean deleteAccount(Long id) { return  accountRepository.deleteAccount(id);}

}
