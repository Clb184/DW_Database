package clb184.WebProject;

import java.sql.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import Clb184.SQL.CSQLServerDriver;
import Clb184.SQL.ISQLDriver;

@CrossOrigin
@RestController
public class Forums {
    
    /*
     * Get data with this json:
     * {
     *      data: [
     *          {
     *              title : "",
     *              content : ""
     *          },
     *          {
     *              title : "",
     *              content : ""
     *          }
     * 
     *      ]
     *      
     * }
     * 
     */
    @GetMapping("/get_forum_data")
    public String GetForumData() {
        ISQLDriver connector = new CSQLServerDriver();
        ResultSet set = null;
        String jsonified = "{\n\t\"data\": [";

        // Create the connector and check if connected succesfuly
        if (false == connector.CreateConnector())
            return "{}";

        // Try to log in
        try {
            Statement statement = connector.GetConnector().createStatement();
            statement.execute("use EcoGreen1;");
            set = statement.executeQuery("select * from Foros;");

            while (set.next()) {
                jsonified = jsonified + "{\n";
                jsonified = jsonified + "  \"title\": \"" + set.getString("titulo") + "\",\n";
                jsonified = jsonified + "  \"contents\": \"" + set.getString("contenido") + "\"\n";
                jsonified = jsonified + "},\n";
            }
            jsonified += "{\"title\": \"\", \"contents\": \"\"}";
            jsonified = jsonified + "]\n}";
            //System.out.print(jsonified);
        } catch (Exception e) {
            System.err.print("Error executing query: " + e + "\n");
        }
        return jsonified;
    }

    @GetMapping("/create_post")
    public void CreatePost(@RequestParam String title, @RequestParam String contents) {
        ISQLDriver connector = new CSQLServerDriver();
        ResultSet set = null;
        
        // Create the connector and check if connected succesfuly
        if (false == connector.CreateConnector()) return;
        
        // Try to log in
        try {
            Statement statement = connector.GetConnector().createStatement();
            statement.execute("use EcoGreen1;");
            statement.execute("insert into Foros (titulo, contenido) values (\'" + title + "\', \'" + contents +"\');");
        } catch (Exception e) {
            System.err.print("Error executing query: " + e + "\n");
        }
    }
    
}