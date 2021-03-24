package repository.user;

import model.User;
import model.builder.UserBuilder;
import model.validation.Notification;
import repository.EntityNotFoundException;
import repository.security.RightsRolesRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Tables.USER;

public class UserRepositoryMySQL implements UserRepository {

    private final Connection connection;
    private final RightsRolesRepository rightsRolesRepository;


    public UserRepositoryMySQL(Connection connection, RightsRolesRepository rightsRolesRepository) {
        this.connection = connection;
        this.rightsRolesRepository = rightsRolesRepository;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<User>();

        try {
            Statement statement = connection.createStatement();
            String fetchUsersSql = "Select * from " + USER;
            ResultSet userResultSet = statement.executeQuery(fetchUsersSql);

            while(userResultSet.next()) {
                userList.add(getUserFromResultSet(userResultSet));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return userList;
    }

//    public Notification<User> findById(Long id){
//        User user;
//
//        try {
//            Statement statement = connection.createStatement();
//            String fetchUserIdSql = "Select * from " + USER + "where `id`=\'" + id;
//            ResultSet userResultSet = statement.executeQuery(fetchUserIdSql);
//
//            if(userResultSet.next()) {
//                user = getUserFromResultSet(userResultSet);
//            }
//            else
//
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//
//    }


    @Override
    public Notification<User> findByUsernameAndPassword(String username, String password) {
        Notification<User> findByUsernameAndPasswordNotification = new Notification<>();
        try {
            Statement statement = connection.createStatement();
            String fetchUserSql = "Select * from `" + USER + "` where `username`=\'" + username + "\' and `password`=\'" + password + "\'";
            ResultSet userResultSet = statement.executeQuery(fetchUserSql);
            if (userResultSet.next()) {
                User user = new UserBuilder()
                        .setUsername(userResultSet.getString("username"))
                        .setPassword(userResultSet.getString("password"))
                        .setRoles(rightsRolesRepository.findRolesForUser(userResultSet.getLong("id")))
                        .build();
                findByUsernameAndPasswordNotification.setResult(user);
                return findByUsernameAndPasswordNotification;
            } else {
                findByUsernameAndPasswordNotification.addError("Invalid email or password!");
                return findByUsernameAndPasswordNotification;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            findByUsernameAndPasswordNotification.addError("Something is wrong with the Database");
        }
        return findByUsernameAndPasswordNotification;
    }

    @Override
    public Notification<User> findByUsername(String username){
        Notification<User> findByUsernameNotification = new Notification<>();
        try {
            Statement statement = connection.createStatement();
            String fetchUserSql = "Select * from `" + USER + "` where `username`=\'" + username + "\'";
            ResultSet userResultSet = statement.executeQuery(fetchUserSql);
            if (userResultSet.next()) {
                User user = new UserBuilder()
                        .setUsername(userResultSet.getString("username"))
                        .setPassword(userResultSet.getString("password"))
                        .build();
                findByUsernameNotification.setResult(user);
                return findByUsernameNotification;
            } else {
                findByUsernameNotification.addError("User not found!");
                return findByUsernameNotification;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            findByUsernameNotification.addError("Something is wrong with the Database");
        }
        return findByUsernameNotification;
    }

    @Override
    public boolean save(User user) {
        try {
            PreparedStatement insertUserStatement = connection
                    .prepareStatement("INSERT INTO user values (null, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            insertUserStatement.setString(1, user.getUsername());
            insertUserStatement.setString(2, user.getPassword());
            insertUserStatement.executeUpdate();

            ResultSet rs = insertUserStatement.getGeneratedKeys();
            rs.next();
            long userId = rs.getLong(1);
            user.setId(userId);

            rightsRolesRepository.addRolesToUser(user, user.getRoles());

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean update(User user) {
        try {
            PreparedStatement updateClientStatement = connection
                    .prepareStatement("UPDATE "+ USER+" SET username= ?,password= ? WHERE id= ?");
            updateClientStatement.setString(1, user.getUsername());
            updateClientStatement.setString(2, user.getPassword());
            updateClientStatement.setLong(3, user.getId());
            updateClientStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeUser(Long id) {
        try {
            String sql = "DELETE from user where id= ?";
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
            String sql = "DELETE from user where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User getUserFromResultSet(ResultSet rs) throws SQLException {
        return new UserBuilder()
                .setId(rs.getLong("id"))
                .setUsername(rs.getString("username"))
                .setPassword(rs.getString("password"))
                .build();
    }


}
