package clb184.WebProject;

import java.sql.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import Clb184.SQL.CMySQLDriver;
import Clb184.SQL.ISQLDriver;

@CrossOrigin
@RestController
public class PageLogin {
    
    @GetMapping("/login")
    public boolean LogIn(@RequestParam String name, @RequestParam String password) {
        ISQLDriver connector = new CMySQLDriver();
        ResultSet set = null;
        boolean ret = false;
        
        // Create the connector and check if connected succesfuly
        if (false == connector.CreateConnector()) return false;
        
        // Try to log in
        try {
            Statement statement = connector.GetConnector().createStatement();
            statement.execute("use EcoGreen;");
            set = statement.executeQuery("select nombre, contraseña from Usuarios where contraseña = \'" + password + "\' and nombre = \'" + name + "\';");
            if(set.next()){
                System.out.print("Successful login\n");
                ret = true;
            } else {
                System.err.print("Incorrect login\n");
            }

        } catch (Exception e) {
            System.err.print("Error executing query: " + e + "\n");
        }
        return ret;
    }
}
