package sample;
import java.sql.*;

public class BaseConnection {

    private static final String DB_URL = "jdbc:h2:mem:testdb";
    private static final String DB_Driver = "org.h2.Driver";

    private Statement statement;

    public BaseConnection() {

        try {
            Class.forName(DB_Driver);
            Connection connection = DriverManager.getConnection(DB_URL);
            statement = connection.createStatement();
            String SQL = "CREATE TABLE calculate " +
                    "(id INT NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR(50)," +
                    "DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
            statement.executeUpdate(SQL);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCalc(String calc) {
        String SQL = "INSERT INTO calculate(name) VALUES ('" + calc + "')";

        try {
            System.out.println(SQL);
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getCalc() {
        String SQL = "SELECT * FROM calculate ORDER BY DATE DESC LIMIT 10";
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

}
