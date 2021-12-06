package homework2.Lesson;

import java.sql.*;
import java.util.Random;

public class JDBCApp {
    private static Connection connection;
    private static Statement statement;
    private static final Random random = new Random();

    public static void main(String[] args) {
        try {
            connect();
            createTable();
            insertStudents();
            insertOneStudent("TEST");
            readData();
            dropTable();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
            e.getMessage();
        } finally {
            disconnect();
        }
    }

    private static void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:javadb.db");
        System.out.println("Connected");
        statement = connection.createStatement();
    }

    private static void disconnect() {

        try {
            if (statement != null) {
                statement.close();
                System.out.println("Statement close");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (connection != null) {
                connection.close();
                System.out.println("Disconnected");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTable() throws SQLException {
        statement.executeUpdate("create table if not exists students(\n" +
                "    id integer primary key autoincrement not null ,\n" +
                "    name text not null ,\n" +
                "    group_name text,\n" +
                "    score integer\n" +
                ")");
    }

    private static void insertStudents() throws SQLException {
        for (int i = 0; i <= 10; i++) {
            statement.executeUpdate("insert into students (name, group_name, score) " +
                    "values ('bob" + i + "','22',3)");
        }
    }

    private static void insertStudentsBatch() {
        try (PreparedStatement ps = connection.prepareStatement(
                "insert into students (name, group_name, score) " +
                        "values (?,?,?)")) {

            for (int i = 0; i < 10; i++) {
                ps.setString(1, "Jack " + i);
                ps.setString(2,"group " + (10-i));
                ps.setInt(3, random.nextInt(6));
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void insertOneStudent(String name) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("insert into students (name, group_name, score) " +
                "values (? ,'22',3)")) {
            ps.setString(1, name);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void readData() {
        try (ResultSet rs = statement.executeQuery("select * from students")) {
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString("name")
                        + " " + rs.getInt(3) + " " + rs.getString("score"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void dropTable() throws SQLException {
        statement.executeUpdate("drop table students");
    }
}
