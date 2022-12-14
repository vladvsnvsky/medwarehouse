package com.mipapp;

import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.LinkedList;

public class DatabaseController {

    private final String url = "jdbc:postgresql://localhost/medwarehouse";
    private final String user = "postgres";
    private final String password = "postgres";

    public void insertUser(User u) throws SQLException, PSQLException {

        String INSERT_USERS_SQL = "INSERT INTO users (name, username, password) VALUES (?, ?, ?);";

        System.out.println(INSERT_USERS_SQL);
        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection(url, user, password);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, u.getName());
            preparedStatement.setString(2, u.getUsername());
            preparedStatement.setString(3, u.getPassword());

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
            printSQLException(e);
        }

        // Step 4: try-with-resource statement will auto close the connection.
    }

    public void insertUser(String _name, String _username, String _password) throws SQLException {

        String INSERT_USERS_SQL = "INSERT INTO users (name, username, password) VALUES (?, ?, ?);";

        System.out.println(INSERT_USERS_SQL);
        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection(url, user, password);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, _name);
            preparedStatement.setString(2, _username);
            preparedStatement.setString(3, _password);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
            printSQLException(e);
        }

        // Step 4: try-with-resource statement will auto close the connection.
    }

    public User getUserById(int _id){

        final String QUERY = "select id,name, username, password from Users where id =?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
            preparedStatement.setInt(1, _id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String username = rs.getString("username");
                String password = rs.getString("password");
                System.out.println(id + "," + name + "," + username + "," + password);
                User res = new User(name, username, password);
                return res;
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return null;
    }

    public LinkedList<User> getAllUsers(){
        final String QUERY = "SELECT * FROM users";
        LinkedList<User> list = new LinkedList<User>();
        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection(url, user, password);
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY);){
            ResultSet res = preparedStatement.executeQuery();
            while(res.next()){
                int id = res.getInt("id");
                String name = res.getString("name");
                String username = res.getString("username");
                String password = res.getString("password");
                User u = new User(name, username, password);
                list.add(u);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return list;
    }

    public void updateUserById(int _id, User newUser){
        final String UPDATE_USERS_SQL = "UPDATE users SET name = ?, username = ?, password = ? WHERE id = ?";
        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection(url, user, password);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL)) {
            preparedStatement.setString(1, newUser.getName());
            preparedStatement.setString(2, newUser.getUsername());
            preparedStatement.setString(3, newUser.getPassword());
            preparedStatement.setInt(4, _id);

            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
            printSQLException(e);
        }

    }

    public void deleteUserById(int _id){
        final String DELETE_USERS_SQL = "DELETE FROM users WHERE id = ?;";

        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection(url, user, password);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USERS_SQL);) {
            preparedStatement.setInt(1, 1);

            // Step 3: Execute the query or update query
            int result = preparedStatement.executeUpdate();
            System.out.println("Number of records affected :: " + result);
        } catch (SQLException e) {

            // print SQL exception information
            printSQLException(e);
        }
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
