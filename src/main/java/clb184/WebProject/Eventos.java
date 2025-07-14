package clb184.WebProject;

import java.sql.*;
import org.springframework.web.bind.annotation.*;

import Clb184.SQL.CMySQLDriver;
import Clb184.SQL.ISQLDriver;

@RestController
public class Eventos {

    // 👉 Crear evento
    @GetMapping("/create_event")
    public void createEvent(
        @RequestParam String nombre,
        @RequestParam String descripcion
    ) {
        ISQLDriver connector = new CMySQLDriver();
        if (!connector.CreateConnector()) return;

        try {
            Statement st = connector.GetConnector().createStatement();
            st.execute("use EcoGreen");

            String sql = String.format(
                "INSERT INTO Eventos (nombre, descripcion, fecha, hora, lugar, organizador_id) " +
                "VALUES ('%s', '%s', '%s', '%s', '%s', %d)",
                nombre, descripcion, null, null, "", null
            );

            st.execute(sql);
        } catch (Exception e) {
            System.err.println("Error al crear evento: " + e);
        }
    }

    // 👉 Obtener todos los eventos
    @GetMapping("/get_event_data")
    public String getEvents() {
        ISQLDriver connector = new CMySQLDriver();
        if (!connector.CreateConnector()) return "{}";

        String jsonified = "{\n\t\"data\": [";
        try {
            Statement st = connector.GetConnector().createStatement();
            st.execute("use EcoGreen");
            ResultSet set = st.executeQuery("SELECT * FROM Eventos");

            while (set.next()) {
                jsonified += "\n\t\t{";
                jsonified += "\"title\": \"" + set.getString("nombre") + "\", ";
                jsonified += "\"description\": \"" + set.getString("descripcion") + "\"";
                jsonified += "},";
            }

            jsonified += "\n\t]\n}";
        } catch (Exception e) {
            System.err.println("Error al obtener eventos: " + e);
        }

        return jsonified;
    }
}
