package Patterns;

import java.sql.*;

public class DBConnector {
    public static void main(String[] a) {
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }
        catch (Exception e) {
            System.out.println(e.getCause());
        }

        // Credentials and URL
        String url = "jdbc:sqlserver://localhost:50132;databaseName=EcoGreen1;trustServerCertificate=true;";
        String user_name = "admin";
        String password = "12345";

        try (
            Connection con = DriverManager.getConnection(url, user_name, password);
                Statement statement = con.createStatement();
                ResultSet res_set = statement.executeQuery("use EcoGreen1");
        ) {
                    System.out.print("Connection established!\n");

        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
