package repository.account;

import model.Account;
import model.Client;
import model.builder.AccountBuilder;
import model.builder.ClientBuilder;
import model.validation.Notification;
import repository.EntityNotFoundException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Tables.ACCOUNT;
import static database.Constants.Tables.CLIENT;


public class AccountRepositoryMySQL implements AccountRepository {

    private final Connection connection;

    public AccountRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from account";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                accounts.add(getAccountFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    @Override
    public Account findById(Long id) {
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from account where id=" + id;
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                return getAccountFromResultSet(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Notification<Account> findByClientId(Long clientId)  {
        Notification<Account> findByClientIdNotification = new Notification<>();
        try {
            Statement statement = connection.createStatement();
            String fetchAccountSql = "Select * from `" + ACCOUNT + "` where `clientId`=\'" + clientId + "\'";
            ResultSet accountResultSet = statement.executeQuery(fetchAccountSql);
            if (accountResultSet.next()) {
                Account account = new AccountBuilder()
                        .setId(accountResultSet.getLong("id"))
                        .setClientId(accountResultSet.getLong("clientId"))
                        .setIdentificationNumber(accountResultSet.getInt("identificationNr"))
                        .setType(accountResultSet.getString("typeOfAccount"))
                        .setAmountOfMoney(accountResultSet.getInt("amountOfMoney"))
                         .setDateOfCreation(accountResultSet.getDate("dateOfCreation"))
                        .build();

                findByClientIdNotification.setResult(account);
                return findByClientIdNotification;
            } else {
                findByClientIdNotification.addError("Account not found!");
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
    public boolean save(Account account) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO account values (null, ?, ?, ?, ?, ?)");
            insertStatement.setLong(1, account.getClientId());
            insertStatement.setInt(2, account.getIdentificationNumber());
            insertStatement.setString(3, account.getType());
            insertStatement.setDouble(4, account.getAmountOfMoney());
            insertStatement.setDate(5, new java.sql.Date(account.getDateOfCreation().getTime()));
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateAccount(Account account) {
        try {
            PreparedStatement updateClientStatement = connection
                    .prepareStatement("UPDATE "+ ACCOUNT +" SET  amountOfMoney= ? WHERE id= ?");
            updateClientStatement.setInt(1, account.getAmountOfMoney());
            updateClientStatement.setLong(2, account.getId());
            updateClientStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean deleteAccount(Long id) {
        try {
            String sql = "DELETE from account where id= ?";
            PreparedStatement deleteStatement = connection.prepareStatement(sql);
            deleteStatement.setLong(1, id);
            deleteStatement.execute();
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
            String sql = "DELETE from account where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Account getAccountFromResultSet(ResultSet rs) throws SQLException {
        return new AccountBuilder()
                .setId(rs.getLong("id"))
                .setClientId(rs.getLong("clientId"))
                .setIdentificationNumber(rs.getInt("identificationNr"))
                .setType(rs.getString("typeOfAccount"))
                .setAmountOfMoney(rs.getInt("amountOfMoney"))
                .setDateOfCreation(new Date(rs.getDate("dateOfCreation").getTime()))
                .build();
    }

}
