package clb184.WebProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import Clb184.SQL.ISQLDriver;

@SpringBootApplication
public class WebProjectApplication {

	// Initalize with some credentials
	public static void InitializeDatabaseDriver() {
		ISQLDriver.SetCredentials("Admin", "12345");
		ISQLDriver.SetServerURL("192.168.100.170");
		ISQLDriver.SetDatabaseName("EcoGreen");
	}

	public static void main(String[] args) {
		InitializeDatabaseDriver();
		SpringApplication.run(WebProjectApplication.class, args);
	}

}
