package org.example.db.Impl;

import org.example.db.ConnectionManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManagerImpl implements ConnectionManager {

    private static String bdDriver;
    private static String dbUrl;
    private static String bdUserName;
    private static String bdPassword;
    static FileInputStream fis;
    static Properties property = new Properties();

    public String getDbUrl() {
        return dbUrl;
    }

    public static void setDbUrl(String dbUrl) {
        ConnectionManagerImpl.dbUrl = dbUrl;
    }

    public String getBdUserName() {
        return bdUserName;
    }

    public static void setBdUserName(String bdUserName) {
        ConnectionManagerImpl.bdUserName = bdUserName;
    }

    public String getBdPassword() {
        return bdPassword;
    }

    public static void setBdPassword(String bdPassword) {
        ConnectionManagerImpl.bdPassword = bdPassword;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            fis = new FileInputStream("src/main/resources/db.properties");
            property.load(fis);

            bdDriver = property.getProperty("driver");
            dbUrl = property.getProperty("url");
            bdUserName = property.getProperty("username");
            bdPassword = property.getProperty("password");
            Class.forName(bdDriver);
            connection = DriverManager.getConnection(dbUrl, bdUserName, bdPassword);
            System.out.println("Connection OK");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection ERROR");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connection;
    }
}