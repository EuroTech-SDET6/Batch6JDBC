package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;

public class jdbc_example {

    String dbUrl = "jdbc:mysql://localhost:3306/SampleDb";
    String dbUsername = "root";
    String dbPassword = "12345678";

    @Test
    public void test1() throws SQLException {
        //create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        // Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ornekdb","root", "Tt.122335");

        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet resultSet = statement.executeQuery("SELECT * FROM customers");


        //how to find how many rows we have for the query
        //go to the last row

        resultSet.last();

        //get the row count
        int rowCount = resultSet.getRow();

        System.out.println(rowCount);

        System.out.println("**********");
        //we need move before first row to get full list since we are at the last row right now
        resultSet.beforeFirst();

        while (resultSet.next()) {
            System.out.println(resultSet.getString(2));
        }
        System.out.println("**********");

        //close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }


    @Test
    public void MetaDataExample() throws SQLException {
        //create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM customers LIMIT 10");

        //get the database related data inside the dbmetadata object
        DatabaseMetaData dbMetadata = connection.getMetaData();


        System.out.println("User =" + dbMetadata.getUserName());
        System.out.println("Database Product Name=" + dbMetadata.getDatabaseProductName());
        System.out.println("Database Product Version =" + dbMetadata.getDatabaseProductVersion());
        System.out.println("Driver Name=" + dbMetadata.getDriverName());
        System.out.println("Driver Version =" + dbMetadata.getDriverVersion());
        System.out.println("DB url =" + dbMetadata.getURL());
        System.out.println("Schemas =" + dbMetadata.getSchemas());

        //get the resultset object metadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();

        //how many columns have?
        int colCount = rsMetadata.getColumnCount();
        System.out.println(colCount);

        //column names
        System.out.println(rsMetadata.getColumnName(1));
        System.out.println(rsMetadata.getColumnName(2));

        System.out.println("----------------------------------");

        //print all the column names dynamically
        for (int i = 1; i <= colCount; i++) {
            System.out.println(rsMetadata.getColumnName(i));
        }

        //close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }


    @Test
    public void test3() throws SQLException {
        //create connection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM customers");

        resultSet.next();
        System.out.println("printing the count: " + resultSet.getInt(1));

        //close all connections
        resultSet.close();
        statement.close();
        connection.close();

    }

}
