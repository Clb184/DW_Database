package Patterns;

import java.sql.*;

public class DBConnector {
    Connection m_Connector = null;

    public boolean CreateDatabaseConnector() {
        // Maybe we got connection or not
        boolean success = false;

        // Try getting the SQL driver
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (Exception e) {
            System.out.println(e.getCause());
            return false;
        }

        // Credentials and URL
        String url = "jdbc:sqlserver://localhost:50132;databaseName=EcoGreen1;trustServerCertificate=true;";
        String user_name = "admin";
        String password = "12345";

        // Try to establish a connection, if failed, show the problem
        try {
            Connection con = DriverManager.getConnection(url, user_name, password);
            System.out.print("Connection established!\n");
            this.m_Connector = con;
            success = true;
        } catch (Exception e) {
            System.out.print("Connection failed: " + e + "\n");
        }
        return success;
    }
    
    public Connection GetConnector() {
        return this.m_Connector;
    }

}
