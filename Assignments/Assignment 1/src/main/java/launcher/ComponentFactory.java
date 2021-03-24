package launcher;

import controller.AdministratorController;
import controller.LoginController;
import controller.EmployeeController;
import database.Constants;
import database.DBConnectionFactory;
import database.SQLTableCreationFactory;
import repository.account.AccountRepository;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryMySQL;
import repository.account.AccountRepositoryMySQL;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.account.AccountService;
import service.account.AccountServiceMySQL;
import service.administrator.AdministratorService;
import service.administrator.AdministratorServiceMySQL;
import service.client.ClientService;
import service.client.ClientServiceMySQL;
import service.user.AuthenticationService;
import service.user.AuthenticationServiceMySQL;
import service.user.UserService;
import service.user.UserServiceMySQL;
import view.AdministratorView;
import view.EmployeeView;
import view.LoginView;

import java.sql.Connection;
import java.sql.SQLData;


public class ComponentFactory {

    private final LoginView loginView;

    private final LoginController loginController;

    private final AdministratorController administratorController;

    private final EmployeeController employeeController;


    private final AuthenticationService authenticationService;

    private final ClientService clientService;

    // for administrator
    private final AdministratorView administratorView;

    // for employee
    private final EmployeeView employeeView;

    private final UserService userService;

    private final AccountService accountService;


    private final UserRepository userRepository;
    private final RightsRolesRepository rightsRolesRepository;
    //
    private final ClientRepositoryMySQL clientRepositoryMySQL;
    //
    private final AccountRepositoryMySQL accountRepositoryMySQL;

    private final ClientRepository clientRepository;

    private final AccountRepository accountRepository;

    private static ComponentFactory instance;

    public static ComponentFactory instance(Boolean componentsForTests) {
        if (instance == null) {
            instance = new ComponentFactory(componentsForTests);
        }
        return instance;
    }

    private ComponentFactory(Boolean componentsForTests) {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(componentsForTests).getConnection();

        this.rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        this.userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);
        this.clientRepository = new ClientRepositoryMySQL(connection);
        this.accountRepository = new AccountRepositoryMySQL(connection);
        this.authenticationService = new AuthenticationServiceMySQL(this.userRepository, this.rightsRolesRepository);
        this.clientService = new ClientServiceMySQL(this.clientRepository);
        this.accountService = new AccountServiceMySQL(this.accountRepository);
        this.loginView = new LoginView();
        this.loginController = new LoginController(loginView, authenticationService);
        this.employeeView = new EmployeeView();
        this.employeeController = new EmployeeController(employeeView, clientService, accountService);
        // administrator
        this.administratorView = new AdministratorView();
        this.userService = new UserServiceMySQL(this.userRepository, this.rightsRolesRepository);


        this.administratorController = new AdministratorController(this.administratorView, this.userService);
        //
        this.clientRepositoryMySQL = new ClientRepositoryMySQL(connection);
        //
        this.accountRepositoryMySQL = new AccountRepositoryMySQL(connection);
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public RightsRolesRepository getRightsRolesRepository() {
        return rightsRolesRepository;
    }

    public LoginView getLoginView() {
        return loginView;
    }

    public AdministratorView getAdministratorView() { return administratorView; }

    public EmployeeView getEmployeeView() { return employeeView; }

    public ClientRepositoryMySQL getClientRepositoryMySQL() {
        return clientRepositoryMySQL;
    }

    public AccountRepositoryMySQL getAccountRepositoryMySQL() {return accountRepositoryMySQL;}

    public LoginController getLoginController() { return loginController;
    }
}
