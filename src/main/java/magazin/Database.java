package magazin;

import magazin.produse.Shampoo;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Database implements AutoCloseable {
    private static Database database = null;
    private final Connection connection;


    public static Database getDatabaseInstance(){
        if (database == null) {
            try {
                database = new Database();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return database;
    }

    private Database() throws Exception {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc-shop?autoReconnect=true&useSSL=false", "root","root13");
        createTables();
    }

    private void createTables() throws SQLException {

        ResultSet results = connection.getMetaData().getTables(null, null, null, new String[]{"TABLE"});
        List<String> databaseTablesName = new ArrayList<>();
        while(results.next())
            databaseTablesName.add(results.getString("TABLE_NAME").toLowerCase());

        if (databaseTablesName.size() != 5){
            HashMap<String, String> tableStatements = new HashMap<>();
            tableStatements.put("parfums", "CREATE TABLE parfums (name varchar(45) primary key, price varchar(45), color varchar(45), " +
                    "distributor varchar(45), parfumType varchar(45), quantity varchar(45), stockName varchar(45))");
            tableStatements.put("shampoos", "CREATE TABLE shampoos (name varchar(45) primary key, price varchar(45), color varchar(45), " +
                    "distributor varchar(45), hairType varchar(45), quantity varchar(45), stockName varchar(45))");
            tableStatements.put("laptops", "CREATE TABLE laptops (name varchar(45) primary key, price varchar(45), color varchar(45), " +
                    "distributor varchar(45), procesor varchar(45), quantity varchar(45), stockName varchar(45))");
            tableStatements.put("cameras", "CREATE TABLE cameras (name varchar(45) primary key, price varchar(45), color varchar(45), " +
                    "distributor varchar(45), megapixels varchar(45), quantity varchar(45), stockName varchar(45))");
            tableStatements.put("distributors", "CREATE TABLE distributors (name varchar(45) primary key, address varchar(45), " +
                    "phoneNumber varchar(45))");
            for(Map.Entry<String, String> table : tableStatements.entrySet()) {
                boolean found = databaseTablesName.contains(table.getKey());
                if (!found) {
                    connection.createStatement().execute(table.getValue());
                }
            }
        }
    }

    public void insertProduct(String[] data, String table) {
        try {
            PreparedStatement st1 = connection.prepareStatement("SELECT * FROM "+table+" WHERE name = ?");
            st1.setString(1, data[0]);
            ResultSet rs = st1.executeQuery();
            if(!rs.next()) {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO " + table + " VALUES (?,?,?,?,?,?,?)");
                for (int parameterIndex = 1; parameterIndex <= 7; parameterIndex++)
                    statement.setString(parameterIndex, data[parameterIndex - 1]);
                int i = statement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertDistributor(String[] data) {
        try {
            PreparedStatement st1 = connection.prepareStatement("SELECT * FROM distributors WHERE name = ?");
            st1.setString(1, data[0]);
            ResultSet rs = st1.executeQuery();
            if(!rs.next()) {
                PreparedStatement statement = connection.prepareStatement("INSERT INTO distributors VALUES (?,?,?)");
                for (int parameterIndex = 1; parameterIndex <= 3; parameterIndex++)
                    statement.setString(parameterIndex, data[parameterIndex - 1]);
                int i = statement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateProductPrice(String table, String name, String newPrice) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE " +table+ " SET price = ? WHERE name =?");
            statement.setString(1, newPrice);
            statement.setString(2, name);
            int i =statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(String name, String table) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM " +table+ " WHERE name = ?");
            statement.setString(1, name);
            int i =statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public void close() throws Exception {
        connection.close();
    }
}
