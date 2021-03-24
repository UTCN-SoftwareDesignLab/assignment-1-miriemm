package repository.user;

import database.Boostraper;
import database.Constants;
import database.DBConnectionFactory;
import database.JDBConnectionWrapper;
import model.Role;
import model.User;
import model.builder.UserBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryMySQL;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserRepositoryMySQLTest {

    private static UserRepository userRepository;
    private static RightsRolesRepository rightsRolesRepository;

    @BeforeClass
    public static void setupClass() {
        JDBConnectionWrapper connectionWrapper = DBConnectionFactory.getConnectionWrapper(true);
        rightsRolesRepository = new RightsRolesRepositoryMySQL(connectionWrapper.getConnection());
        userRepository = new UserRepositoryMySQL(connectionWrapper.getConnection(), rightsRolesRepository);
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
        Assert.assertTrue(userRepository.findAll().isEmpty());

        List<Role> testRoles = new ArrayList<Role>();
        testRoles.add(rightsRolesRepository.findRoleById(1L));

        User testUser = new UserBuilder().setUsername("test")
                .setPassword("123")
                .setRoles(testRoles)
                .build();

        userRepository.save(testUser);

        Assert.assertFalse(userRepository.findAll().isEmpty());
        Assert.assertTrue(userRepository.findAll().size() == 1);
    }

    @Test
    public void findByUsernameAndPassword() {
        Assert.assertTrue(userRepository.findAll().isEmpty());

        List<Role> testRoles = new ArrayList<Role>();
        testRoles.add(rightsRolesRepository.findRoleById(1L));

        User testUser = new UserBuilder().setUsername("test")
                .setPassword("123")
                .setRoles(testRoles)
                .build();

        userRepository.save(testUser);

        User resultedUser = userRepository.findByUsernameAndPassword("test", "123").getResult();
        Assert.assertNotNull(resultedUser);
        Assert.assertTrue(resultedUser.getUsername().equals("test"));
        Assert.assertTrue(resultedUser.getPassword().equals("123"));

    }

    @Test
    public void findByUsername() {
        Assert.assertTrue(userRepository.findAll().isEmpty());

        List<Role> testRoles = new ArrayList<Role>();
        testRoles.add(rightsRolesRepository.findRoleById(1L));

        User testUser = new UserBuilder().setUsername("test")
                .setPassword("123")
                .setRoles(testRoles)
                .build();

        userRepository.save(testUser);

        User resultedUser = userRepository.findByUsername("test").getResult();
        Assert.assertNotNull(resultedUser);
        Assert.assertTrue(resultedUser.getUsername().equals("test"));
    }

    @Test
    public void save() {
        Assert.assertTrue(userRepository.findAll().isEmpty());

        List<Role> testRoles = new ArrayList<Role>();
        testRoles.add(rightsRolesRepository.findRoleById(1L));

        User testUser = new UserBuilder().setUsername("test")
                .setPassword("123")
                .setRoles(testRoles)
                .build();

        userRepository.save(testUser);

        Assert.assertTrue(userRepository.findAll().size() == 1);
    }

    @Test
    public void update() {
        Assert.assertTrue(userRepository.findAll().isEmpty());

        List<Role> testRoles = new ArrayList<Role>();
        testRoles.add(rightsRolesRepository.findRoleById(1L));

        User testUser = new UserBuilder().setUsername("test")
                .setPassword("123")
                .setRoles(testRoles)
                .setId(1L)
                .build();

        userRepository.save(testUser);

        testUser.setUsername("new username");

        userRepository.update(testUser);

        Assert.assertNotNull(userRepository.findByUsername("new username").getResult());
    }

    @Test
    public void removeUser() {
        Assert.assertTrue(userRepository.findAll().isEmpty());

        List<Role> testRoles = new ArrayList<Role>();
        testRoles.add(rightsRolesRepository.findRoleById(1L));

        User testUser = new UserBuilder().setUsername("test")
                .setPassword("123")
                .setRoles(testRoles)
                .setId(1L)
                .build();

        userRepository.save(testUser);

        Assert.assertTrue(userRepository.findAll().size() == 1);

        userRepository.removeUser(1L);

        Assert.assertTrue(userRepository.findAll().isEmpty());
    }

    @Test
    public void removeAll() {
        Assert.assertTrue(userRepository.findAll().isEmpty());

        List<Role> testRoles = new ArrayList<Role>();
        testRoles.add(rightsRolesRepository.findRoleById(1L));

        User testUser = new UserBuilder().setUsername("test")
                .setPassword("123")
                .setRoles(testRoles)
                .setId(1L)
                .build();

        userRepository.save(testUser);

        User testUser2 = new UserBuilder().setUsername("test2")
                .setPassword("123")
                .setRoles(testRoles)
                .setId(2L)
                .build();

        userRepository.save(testUser2);

        Assert.assertTrue(userRepository.findAll().size() == 2);

        userRepository.removeAll();

        Assert.assertTrue(userRepository.findAll().isEmpty());
    }
}