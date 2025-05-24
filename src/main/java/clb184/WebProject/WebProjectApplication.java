package clb184.WebProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import Clb184.SQL.ISQLDriver;

@SpringBootApplication
public class WebProjectApplication {

	// Initalize with some credentials
	public static void InitializeDatabaseDriver() {
		ISQLDriver.SetCredentials("admin", "12345");
		ISQLDriver.SetServerURL("localhost", 50132);
		ISQLDriver.SetDatabaseName("EcoGreen1");
	}

	public static void main(String[] args) {
		InitializeDatabaseDriver();
		SpringApplication.run(WebProjectApplication.class, args);
	}

}
