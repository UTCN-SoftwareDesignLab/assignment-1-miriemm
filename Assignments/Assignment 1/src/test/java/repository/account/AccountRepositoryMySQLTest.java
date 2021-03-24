package repository.account;

import database.Boostraper;
import database.DBConnectionFactory;
import database.JDBConnectionWrapper;
import model.Account;
import model.Client;
import model.builder.AccountBuilder;
import model.builder.ClientBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.EntityNotFoundException;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryMySQL;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class AccountRepositoryMySQLTest {

    private static ClientRepository clientRepository;
    private static AccountRepository accountRepository;

    @BeforeClass
    public static void setupClass() {
        JDBConnectionWrapper connectionWrapper = DBConnectionFactory.getConnectionWrapper(true);
        accountRepository = new AccountRepositoryMySQL(connectionWrapper.getConnection());
        clientRepository = new ClientRepositoryMySQL(connectionWrapper.getConnection());


    }

    @Before
    public void cleanUp() {
        try {
            new Boostraper().execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Test
    public void findAll() {
        List<Account> allAccounts = accountRepository.findAll();

        Assert.assertTrue(allAccounts.isEmpty());


        Client testClient = new ClientBuilder()
                .setId(1L)
                .setName("Test Name")
                .setIdentityCardNr("82727")
                .setPersonalNumericCode("2018928722")
                .setAddress("Cluj-Napoca")
                .build();

        clientRepository.save(testClient);

        int nrInsertedAccounts = 3;
        for (int i = 0; i < nrInsertedAccounts; i++) {
            Account testAccount = new AccountBuilder().setClientId(testClient.getId())
                    .setIdentificationNumber(i)
                    .setType("testType")
                    .setAmountOfMoney(9000)
                    .setDateOfCreation(new Date())
                    .build();
                    accountRepository.save(testAccount);
        }

        List<Account> newAccounts = accountRepository.findAll();

        Assert.assertEquals(nrInsertedAccounts, newAccounts.size());

    }

    @Test
    public void findById() {

        Client testClient = new ClientBuilder()
                .setId(1L)
                .setName("Test Name")
                .setIdentityCardNr("82727")
                .setPersonalNumericCode("2018928722")
                .setAddress("Cluj-Napoca")
                .build();

        clientRepository.save(testClient);

        Account testAccount = new AccountBuilder().setClientId(testClient.getId())
                .setIdentificationNumber(32323245)
                .setType("testType")
                .setAmountOfMoney(9000)
                .setDateOfCreation(new Date())
                .build();

        accountRepository.save(testAccount);

        long EXISTING_ACCOUNT_ID = 1L;
        Account account = accountRepository.findById(EXISTING_ACCOUNT_ID);
        Assert.assertTrue(account.getId() == EXISTING_ACCOUNT_ID);

        long NON_EXISTING_ID = 100L;
        Account nonExistingAccount = accountRepository.findById(NON_EXISTING_ID);
        Assert.assertNull(nonExistingAccount);
    }

    @Test
    public void findByClientId() {

        Client testClient = new ClientBuilder()
                .setId(1L)
                .setName("Test Name")
                .setIdentityCardNr("82727")
                .setPersonalNumericCode("2018928722")
                .setAddress("Cluj-Napoca")
                .build();

        clientRepository.save(testClient);

        Account testAccount = new AccountBuilder().setClientId(testClient.getId())
                .setAmountOfMoney(9000)
                .setType("testType")
                .setIdentificationNumber(32323245)
                .setDateOfCreation(new Date())
                .build();
        accountRepository.save(testAccount);


        long EXISTING_CLIENT_ID = testClient.getId();
        Account account = accountRepository.findByClientId(EXISTING_CLIENT_ID).getResult();
        Assert.assertTrue(testAccount.getIdentificationNumber().intValue() == account.getIdentificationNumber().intValue());

    }

    @Test
    public void save() {

        Client testClient = new ClientBuilder()
                .setId(1L)
                .setName("Test Name")
                .setIdentityCardNr("82727")
                .setPersonalNumericCode("2018928722")
                .setAddress("Cluj-Napoca")
                .build();

        clientRepository.save(testClient);

        Account testAccount = new AccountBuilder().setClientId(testClient.getId())
                .setAmountOfMoney(9000)
                .setType("testType")
                .setIdentificationNumber(32323245)
                .setDateOfCreation(new Date())
                .build();
        int size = accountRepository.findAll().size();

        accountRepository.save(testAccount);

        int sizeAfterSave = accountRepository.findAll().size();

        Assert.assertTrue(sizeAfterSave == size + 1 );
        Assert.assertNotNull(accountRepository.findByClientId(testAccount.getClientId()));

    }

    @Test
    public void updateAccount() {

        Client testClient = new ClientBuilder()
                .setId(1L)
                .setName("Test Name")
                .setIdentityCardNr("82727")
                .setPersonalNumericCode("2018928722")
                .setAddress("Cluj-Napoca")
                .build();

        clientRepository.save(testClient);

        Account testAccount = new AccountBuilder()
                .setId(1L)
                .setClientId(testClient.getId())
                .setAmountOfMoney(9000)
                .setType("testType")
                .setIdentificationNumber(32323245)
                .setDateOfCreation(new Date())
                .build();
        accountRepository.save(testAccount);

        testAccount.setAmountOfMoney(10);

        accountRepository.updateAccount(testAccount);


        Account account = accountRepository.findByClientId(testClient.getId()).getResult();


        int money = account.getAmountOfMoney().intValue();

        Assert.assertEquals(10, money);

    }

    @Test
    public void deleteAccount() {
        Client testClient = new ClientBuilder()
                .setId(1L)
                .setName("Test Name")
                .setIdentityCardNr("82727")
                .setPersonalNumericCode("2018928722")
                .setAddress("Cluj-Napoca")
                .build();

        clientRepository.save(testClient);

        Account testAccount = new AccountBuilder().setId(1L)
                .setClientId(testClient.getId())
                .setAmountOfMoney(9000)
                .setType("testType")
                .setIdentificationNumber(32323245)
                .setDateOfCreation(new Date())
                .build();

        Long accountId = testAccount.getId();

        accountRepository.save(testAccount); // I have created an account

        int size = accountRepository.findAll().size();

        accountRepository.deleteAccount(accountId);

        int sizeAfterDelete = accountRepository.findAll().size();


        Assert.assertTrue(size - 1 == sizeAfterDelete );
        Assert.assertTrue(accountRepository.findByClientId(testAccount.getClientId()).hasErrors());

    }

    @Test
    public void removeAll() {
        Client testClient = new ClientBuilder()
                .setId(1L)
                .setName("Test Name")
                .setIdentityCardNr("82727")
                .setPersonalNumericCode("2018928722")
                .setAddress("Cluj-Napoca")
                .build();

        clientRepository.save(testClient);

        Account testAccount = new AccountBuilder().setId(1L)
                .setClientId(testClient.getId())
                .setAmountOfMoney(9000)
                .setType("testType")
                .setIdentificationNumber(32323245)
                .setDateOfCreation(new Date())
                .build();

        Account testAccount2 = new AccountBuilder().setId(2L)
                .setClientId(testClient.getId())
                .setAmountOfMoney(8000)
                .setType("testType2")
                .setIdentificationNumber(66323245)
                .setDateOfCreation(new Date())
                .build();

        accountRepository.save(testAccount);
        accountRepository.save(testAccount2);

        accountRepository.removeAll();
        List<Account> accounts = accountRepository.findAll();
        Assert.assertEquals(0, accounts.size());
    }
}