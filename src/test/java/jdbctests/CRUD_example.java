package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;

public class CRUD_example {

    String dbUrl = "jdbc:mysql://localhost:3306/SampleDb";
    String dbUsername = "root";
    String dbPassword = "12345678";

    @Test
    public void createRecord() throws SQLException {

        //create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String queryOne = "INSERT INTO customers(customerNumber,customerName,contactLastName,contactFirstName,phone,addressLine1,addressLine2,city_id,town_id,postalCode,country_id,salesRepEmployeeNumber,creditLimit)"
                            + "Values(555,'Eurotech','Tech','Euro ','40.32.2555','54, London','United Kingdom',1,1,44000,7,1370,21000.00)";

        int affectedRows = statement.executeUpdate(queryOne);
        System.out.println("Number of rows affected by the insert query: " + affectedRows);

        // close connection
        statement.close();
        connection.close();
    }

    @Test
    public void readRecord() throws SQLException {

        //create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String queryOne = "SELECT * FROM customers WHERE customerNumber=555";

        ResultSet resultSet = statement.executeQuery(queryOne);
        resultSet.next();
        System.out.println("Customer Number: " + resultSet.getString("customerNumber"));
        System.out.println("Customer Name: " + resultSet.getString("customerName"));

        // close connection
        resultSet.close();
        statement.close();
        connection.close();
    }


    @Test
    public void updateRecord() throws SQLException {

        //create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String queryOne = "UPDATE customers SET customerName = 'EurotechCourse' WHERE customerNumber=555";

        int affectedRows = statement.executeUpdate(queryOne);
        System.out.println("Number of rows affected by the insert query: " + affectedRows);

        String queryTwo = "SELECT * FROM customers WHERE  customerNumber=555";
        ResultSet resultSet = statement.executeQuery(queryTwo);
        resultSet.next();
        System.out.println("Updated value of column customerName: " + resultSet.getString("customerName"));

        // close connection
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void deleteRecord() throws SQLException {

        //create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        String queryOne = "DELETE FROM customers WHERE customerNumber=555";

        int affectedRows = statement.executeUpdate(queryOne);
        System.out.println("Number of rows affected by the insert query: " + affectedRows);

        // close connection
        statement.close();
        connection.close();
    }

}
