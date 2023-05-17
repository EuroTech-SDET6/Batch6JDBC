package jdbctests;

import java.sql.*;


public class Main {

    public static void main(String[] args) throws SQLException {

        String dbUrl = "jdbc:mysql://127.0.0.1/SampleDb";
//        String dbUrl = "jdbc:mysql://localhost/SampleDb";
        String dbUsername = "root";
        String dbPassword = "12345678";

        // create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

        // create a statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        // run query and get result in ResultSet object
        ResultSet resultSet = statement.executeQuery("SELECT * FROM customers;");

//        ResultSet resultSet2 = statement.executeQuery("SELECT * FROM country;");

        // move the pointer to the first row
        resultSet.next();

        // getting information with column Name
        System.out.println(resultSet.getInt("customerNumber") + "-" + resultSet.getString("customerName"));

        System.out.println("*******************");

        // getting information with column Index
        System.out.println(resultSet.getInt(1) + "-" + resultSet.getString(2));

        System.out.println("*******************");

        // move pointer to the second row
        resultSet.next();
        System.out.println(resultSet.getInt(1) + "-" + resultSet.getString("customerName") + "-" + resultSet.getString("contactFirstName") + " " + resultSet.getString("contactLastName"));

        System.out.println("______ Closing the connection _____");
        resultSet.close();
        statement.close();
        connection.close();


    }

}
