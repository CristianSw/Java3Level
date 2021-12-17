package homework3.chat.server;

import homework3.chat.client.Clients;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DBAuthService implements AuthServiceInterface {

    private static Connection connection;
    private static Statement statement;
    private Map<String, Clients> clientsMap = new HashMap<>();
    private String sqlQuery = "SELECT login, passwd, username FROM users;";

    public DBAuthService() {
        try {
            start();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try(ResultSet resultSet = statement.executeQuery(sqlQuery))  {
                while (resultSet.next()) {
                    String login = resultSet.getString(1);
                    String passwd = resultSet.getString(2);
                    String username = resultSet.getString(3);
                    clientsMap.put(login, new Clients(login, passwd, username));
                }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            stop();
        }
    }


    @Override
    public void start() throws  SQLException {
        System.out.println("Auth Service is starting.");
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:chat_users.sqlite");
            System.out.println("Connection created.");
            statement = connection.createStatement();
            System.out.println("Statement created");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void stop() {
        System.out.println("Auth Service is stopping.");
        try {
            if (statement != null) {
                statement.close();
                System.out.println("Statement closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String findUserByLoginAndPassword(String login, String passwd) {

        for (Clients c : clientsMap.values()) {
            if (c.getLogin().equalsIgnoreCase(login) && c.getPasswd().equalsIgnoreCase(passwd)) {
                return c.getUsername();
            }
        }
        return null;
    }
}
