package sample;


import org.w3c.dom.ls.LSOutput;

import java.sql.*;

public class BaseConnection {

    private static final String DB_URL = "jdbc:h2:mem:testdb";
    private static final String DB_Driver = "org.h2.Driver";

    private Connection connection;
    private Statement statement;

    public BaseConnection() {

        try {
            Class.forName(DB_Driver);
            connection = DriverManager.getConnection(DB_URL);
            statement = connection.createStatement();
            String SQL = "CREATE TABLE calculate " +
                    "(id INT NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR(50)," +
                    "DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
            statement.executeUpdate(SQL);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("JDBC драйвер для СУБД не найден!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка SQL !");
        }
    }

    public void addCalc(String calc) {
        String SQL = "INSERT INTO calculate(name) VALUES ('" + calc + "')";

        String requset = "SELECT * FROM calculate";
        try {
            System.out.println(SQL);
            statement.executeUpdate(SQL);
            ResultSet resultSet = statement.executeQuery(requset);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);



            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getCalc() {
        String SQL = "SELECT * FROM calculate ORDER BY DATE DESC LIMIT 10";
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(SQL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

}
