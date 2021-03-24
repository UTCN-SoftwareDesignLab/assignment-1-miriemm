package repository.client;

import model.Account;
import model.Client;
import model.User;
import model.builder.AccountBuilder;
import model.builder.ClientBuilder;
import model.builder.UserBuilder;
import model.validation.Notification;
import repository.EntityNotFoundException;
import repository.account.AccountRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Tables.CLIENT;
import static database.Constants.Tables.USER;


public class ClientRepositoryMySQL implements ClientRepository {

    private final Connection connection;

    public ClientRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from client";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                clients.add(getClientFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }

    @Override

    public Notification<Client> findByName(String name) {
        Notification<Client> findByNameNotification = new Notification<>();
        try {
            Statement clientStatement = connection.createStatement();
            String fetchClientSql = "Select * from `" + CLIENT + "` where `name`=\'" + name + "\'";
            ResultSet clientResultSet = clientStatement.executeQuery(fetchClientSql);
            if (clientResultSet.next()) {
                Client client = new ClientBuilder()
                        .setId(clientResultSet.getLong("id"))
                        .setName(clientResultSet.getString("name"))
                        .setIdentityCardNr(clientResultSet.getString("identityCardNr"))
                        .setPersonalNumericCode(clientResultSet.getString("personalNumericCode"))
                        .setAddress(clientResultSet.getString("address"))
                        .build();
                findByNameNotification.setResult(client);
                return findByNameNotification;
            } else {
                findByNameNotification.addError("Client not found!");
                return findByNameNotification;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            findByNameNotification.addError("Something is wrong with the Database");
        }
        return findByNameNotification;
    }

    @Override
    public Notification<Client> findById(Long id) {
        Notification<Client> findByClientIdNotification = new Notification<>();
        try {
        Statement statement = connection.createStatement();
        String fetchClientSql = "Select * from `" + CLIENT + "` where `id`=\'" + id + "\'";
        ResultSet clientResultSet = statement.executeQuery(fetchClientSql);
        if (clientResultSet.next()) {
            Client client = new ClientBuilder()
                    .setId(clientResultSet.getLong("id"))
                    .setName(clientResultSet.getString("name"))
                    .setIdentityCardNr(clientResultSet.getString("identityCardNr"))
                    .setPersonalNumericCode(clientResultSet.getString("personalNumericCode"))
                    .setAddress(clientResultSet.getString("address"))
                    .build();

            findByClientIdNotification.setResult(client);
            return findByClientIdNotification;
        } else {
            findByClientIdNotification.addError("Client not found!");
            return findByClientIdNotification;
            }
        } catch( SQLException e)

        {
            e.printStackTrace();
            findByClientIdNotification.addError("Something is wrong with the Database");
        }
            return findByClientIdNotification;
    }

    @Override
    public boolean save(Client client) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO client values (null, ?, ?, ?, ?)");
            insertStatement.setString(1, client.getName());
            insertStatement.setString(2, client.getIdentityCardNr());
            insertStatement.setString(3, client.getPersonalNumericCode());
            insertStatement.setString(4, client.getAddress());
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Client client) {
        try {
            PreparedStatement updateClientStatement = connection
                    .prepareStatement("UPDATE "+ CLIENT +" SET name= ?,identityCardNr= ?,personalNumericCode= ?,address= ? WHERE id= ?");
            updateClientStatement.setString(1, client.getName());
            updateClientStatement.setString(2, client.getIdentityCardNr());
            updateClientStatement.setString(3, client.getPersonalNumericCode());
            updateClientStatement.setString(4, client.getAddress());
            updateClientStatement.setLong(5, client.getId());
            updateClientStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from client where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Client getClientFromResultSet(ResultSet rs) throws SQLException {
        return new ClientBuilder()
                .setId(rs.getLong("id"))
                .setName(rs.getString("name"))
                .setIdentityCardNr(rs.getString("identityCardNr"))
                .setPersonalNumericCode(rs.getString("personalNumericCode"))
                .setAddress(rs.getString("address"))
                .build();
    }

}
