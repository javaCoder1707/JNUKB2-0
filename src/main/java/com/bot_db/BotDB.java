package com.bot_db;

import java.sql.*;

public class BotDB {
    private static final String URL = "jdbc:postgresql://localhost:5432/bot_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1707";

    private static boolean hasUser(int id){
        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); Statement statement = connection.createStatement()) {
            Driver driver = new org.postgresql.Driver();
            DriverManager.registerDriver(driver);

            ResultSet resultSet = statement.executeQuery("SELECT * FROM user_bot");
            while(resultSet.next()){
                if(resultSet.getInt("id") == id){
                    return true;
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }

    public static boolean addUser(String firstName, String lastName, int id){

        String sql = "INSERT INTO user_bot(id, first_name, last_name) VALUES((?), (?), (?))";

        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD); final PreparedStatement statement = connection.prepareStatement(sql)) {
            Driver driver = new org.postgresql.Driver();
            DriverManager.registerDriver(driver);

            if(!hasUser(id)){
                statement.setInt(1, id);
                statement.setString(2, firstName);
                statement.setString(3, lastName);

                System.out.println("INSERT " + statement.executeUpdate());

                return true;
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return false;
    }
}
