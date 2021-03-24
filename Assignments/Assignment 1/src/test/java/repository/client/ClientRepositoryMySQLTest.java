package repository.client;

import database.Boostraper;
import database.DBConnectionFactory;
import database.JDBConnectionWrapper;
import model.Account;
import model.Client;
import model.builder.AccountBuilder;
import model.builder.ClientBuilder;
import model.validation.Notification;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import repository.account.AccountRepository;
import repository.account.AccountRepositoryMySQL;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryMySQL;

import org.junit.Test;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ClientRepositoryMySQLTest {

    private static ClientRepository clientRepository;

    @BeforeClass
    public static void setupClass() {
        JDBConnectionWrapper connectionWrapper = DBConnectionFactory.getConnectionWrapper(true);
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
        Client testClient = new ClientBuilder()
                .setName("Test Name")
                .setIdentityCardNr("5321")
                .setPersonalNumericCode("4253532")
                .setAddress("Cluj-Napoca")
                .build();

        clientRepository.save(testClient);

        List<Client> clients = clientRepository.findAll();
        int insertedClientsNr = 1;

        Assert.assertEquals(insertedClientsNr, clients.size() );

    }

    @Test
    public void findByName() {
        Client testClient = new ClientBuilder()
                .setName("Client")
                .setIdentityCardNr("5321")
                .setPersonalNumericCode("4253532")
                .setAddress("Cluj-Napoca")
                .build();

        clientRepository.save(testClient);

        Notification<Client> client = clientRepository.findByName(testClient.getName());

        Assert.assertFalse(client.hasErrors());
        Assert.assertTrue(client.getResult().getPersonalNumericCode().equals(testClient.getPersonalNumericCode()) );

    }

    @Test
    public void findById() {
        Client testClient = new ClientBuilder()
                .setId(1L)
                .setName("Client")
                .setIdentityCardNr("5434")
                .setPersonalNumericCode("122452")
                .setAddress("Cluj-Napoca")
                .build();

        clientRepository.save(testClient);

        Notification<Client> client = clientRepository.findById(testClient.getId());

        Assert.assertFalse(client.hasErrors());
        Assert.assertTrue(client.getResult().getPersonalNumericCode().equals(testClient.getPersonalNumericCode()) );

    }

    @Test
    public void save() {

        Client testClient = new ClientBuilder()
                .setName("Client")
                .setIdentityCardNr("5321")
                .setPersonalNumericCode("4253532")
                .setAddress("Cluj-Napoca")
                .build();

        clientRepository.save(testClient);

        Assert.assertFalse(clientRepository.findAll().isEmpty());

    }

    @Test
    public void update() {
        Client testClient = new ClientBuilder()
                .setId(1L)
                .setName("Client")
                .setIdentityCardNr("5434")
                .setPersonalNumericCode("122452")
                .setAddress("Cluj-Napoca")
                .build();

        clientRepository.save(testClient);

        testClient.setName("Updated Client Name");

        clientRepository.update(testClient);

        Assert.assertNotNull(clientRepository.findByName("Updated Client Name").getResult());
    }

    @Test
    public void removeAll() {
        Client testClient = new ClientBuilder()
                .setId(1L)
                .setName("Client")
                .setIdentityCardNr("5434")
                .setPersonalNumericCode("122452")
                .setAddress("Cluj-Napoca")
                .build();

        clientRepository.save(testClient);

        Client testClient2 = new ClientBuilder()
                .setId(2L)
                .setName("Client2")
                .setIdentityCardNr("5434")
                .setPersonalNumericCode("122452")
                .setAddress("Cluj-Napoca")
                .build();

        clientRepository.save(testClient2);

        clientRepository.removeAll();

        Assert.assertTrue(clientRepository.findAll().isEmpty());
    }
}