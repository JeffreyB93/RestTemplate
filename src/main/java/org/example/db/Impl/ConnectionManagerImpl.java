package org.example.db.Impl;

import org.example.db.ConnectionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManagerImpl implements ConnectionManager {

    private final String DB_DRIVER = "org.postgresql.Driver";
    private static String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
    private static String bdUserName = "habrpguser";
    private static String bdPassword = "pgpwd4habr";

    public static String getDbUrl() {
        return dbUrl;
    }

    public static void setDbUrl(String dbUrl) {
        ConnectionManagerImpl.dbUrl = dbUrl;
    }

    public static String getBdUserName() {
        return bdUserName;
    }

    public static void setBdUserName(String bdUserName) {
        ConnectionManagerImpl.bdUserName = bdUserName;
    }

    public static String getBdPassword() {
        return bdPassword;
    }

    public static void setBdPassword(String bdPassword) {
        ConnectionManagerImpl.bdPassword = bdPassword;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(dbUrl, bdUserName, bdPassword);
            System.out.println("Connection OK");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Connection ERROR");
        }
        return connection;
    }
}