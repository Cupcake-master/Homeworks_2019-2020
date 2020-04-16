package ru.bulat.dao;

import ru.bulat.model.User;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5433/postgres";
            String username = "postgres";
            String password = "543216789";
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public static void recordNewData(User user){
        try (PreparedStatement preparedStatement = getInstance().getConnection().prepareStatement
                ("INSERT INTO huser(name, dateofbirth) VALUES (?, ?)")) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getBirthday());
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public static void receivingDataFromAllUsers(){
        try (PreparedStatement preparedStatement = getInstance().getConnection().prepareStatement("SELECT * FROM huser")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("Id");
                String name = resultSet.getString("Name");
                String date = resultSet.getString("DateOfBirth");
                System.out.println(id + " " + name + " " + date);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public static ArrayList<User> gettingUsers(){
        ArrayList<User> users = new ArrayList<User>();
        try (PreparedStatement preparedStatement = getInstance().getConnection().prepareStatement("SELECT * FROM huser")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("Id");
                String name = resultSet.getString("Name");
                String date = resultSet.getString("DateOfBirth");

                User user = new User(name, date);
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return users;
    }
}