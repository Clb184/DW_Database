package clb184.WebProject;

import java.sql.*;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ser.std.UUIDSerializer;

import Patterns.DBConnector;

@CrossOrigin
@RestController
public class PageLogin {
    
    @GetMapping("/login")
    public boolean LogIn(@RequestParam String name, @RequestParam String password) {
        DBConnector connector = new DBConnector();
        connector.CreateDatabaseConnector();
        ResultSet set = null;
        boolean ret = false;
        try {
            Statement statement = connector.GetConnector().createStatement();
            statement.execute("use EcoGreen1;");
            set = statement.executeQuery("select nombre, contraseña from Usuarios where contraseña = \'" +  password + "\' and nombre = \'" + name + "\'");
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
