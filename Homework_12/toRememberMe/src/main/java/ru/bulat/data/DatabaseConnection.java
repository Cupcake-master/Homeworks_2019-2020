package ru.bulat.data;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5433/postgres";
            String password = "543216789";
            String username = "postgres";
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    private Connection getConnection() {
        return connection;
    }

    private static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }

        return instance;
    }

    public static int writeToDatabaseNewUser(String email, String password, String telephoneNumber, String dateOfBirth,
                                             String gender, String country, String about_myself) {
        //language=Postgres
        try (PreparedStatement preparedStatement = getInstance().getConnection().prepareStatement
                ("INSERT INTO iuser(email, password, telephone_number, date_of_birth, gender, country, about_myself)" +
                        "VALUES(?, ?, ?, ?, ?, ?, ?) RETURNING id")) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, telephoneNumber);
            preparedStatement.setString(4, dateOfBirth);
            preparedStatement.setString(5, gender);
            preparedStatement.setString(6, country);
            preparedStatement.setString(7, about_myself);
            preparedStatement.execute();
            ResultSet lastUpdatedId = preparedStatement.getResultSet();
            if (lastUpdatedId.next()) {
                return lastUpdatedId.getInt(1);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return -1;
    }

    public static int userVerification(String email, String password) {
        //language=Postgres
        try (PreparedStatement preparedStatement = getInstance().getConnection().prepareStatement("SELECT id, password FROM iuser where email = ?")) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String normalPassword = resultSet.getString("password");
                if (password.equals(normalPassword)) return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return -1;
    }

    public static ArrayList<Integer> gettingAllIdGroups(int idUser) {
        ArrayList<Integer> groups = new ArrayList<>();
        try (PreparedStatement preparedStatement = getInstance().getConnection().prepareStatement("SELECT id_group FROM itogether where id_users = ?")) {
            preparedStatement.setInt(1, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idGroup = resultSet.getInt("id_group");
                groups.add(idGroup);
            }
            return groups;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return groups;
    }

    public static String gettingNameGroup(int idGroup){
        try (PreparedStatement preparedStatement = getInstance().getConnection().prepareStatement("SELECT class FROM igroup where id = ?")) {
            preparedStatement.setInt(1, idGroup);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("class");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return "";
    }


    public static void recordGroupsForUsers(int idUser, String group){
        int idGroup = gettingIdGroup(group);
        try (PreparedStatement preparedStatement = getInstance().getConnection().prepareStatement
                ("INSERT INTO itogether(id_users, id_group) VALUES (?, ?)")) {
            preparedStatement.setInt(1, idUser);
            preparedStatement.setInt(2, idGroup);
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    private static int gettingIdGroup(String group){
        try (PreparedStatement preparedStatement = getInstance().getConnection().prepareStatement
                ("SELECT id from igroup where class = ?")) {
            preparedStatement.setString(1, group);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return -1;
    }

    public static ArrayList<Integer> gettingAllExistingIdGroups(){
        ArrayList<Integer> allExistingIdGroups = new ArrayList<>();
        try (PreparedStatement preparedStatement = getInstance().getConnection().prepareStatement
                ("SELECT id from igroup")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                allExistingIdGroups.add(resultSet.getInt("id"));
            }
            return allExistingIdGroups;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return null;
    }

    public static void writeNewGroup(String group){
        try (PreparedStatement preparedStatement = getInstance().getConnection().prepareStatement
                ("INSERT INTO igroup(class) VALUES (?)")) {
            preparedStatement.setString(1, group);
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public static void writingCookiesToTheDatabase(String cookie){
        try (PreparedStatement preparedStatement = getInstance().getConnection().prepareStatement
                ("INSERT INTO iremember(cookie) VALUES (?)")) {
            preparedStatement.setString(1, cookie);
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }
}
