package homework2.chat.server;

import java.sql.*;
import java.util.Optional;

public class DBAuthService implements AuthService {
    private static Connection connection;
    private static Statement statement;

    @Override
    public void start() {
        try {
            connect();
        } catch (SQLException e) {
            e.printStackTrace();
            e.getCause();
        }
        System.out.println("Auth with DB service is running");

    }

    @Override
    public void stop() {
        disconnect();
    }

    @Override
    public Optional<String> getNickByLoginAndPass(String login, String passwd) {
        return getUser(login,passwd);
    }

    private static void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:chat_users.sqlite");
        statement = connection.createStatement();
        System.out.println("Connected and statement created");
    }

    private static Optional<String> getUser(final String login, final String passwd) {
        Optional<String> optional = null;
        String credentials = "";
        try (ResultSet rs = statement.executeQuery("select login, passwd from users" +
                "where login =" + login + "and passwd =" + passwd)) {
            while (rs.next()){
                credentials = rs.getString("login") + " " + rs.getString(passwd);
            }
            optional = Optional.of(credentials);
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return optional;
    }
    private static void disconnect(){
        try {
            if (statement != null){
                statement.close();
                System.out.println("Statement closed.");
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }

        try {
            if (connection != null){
                connection.close();
                System.out.println("Connection closed.");
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
