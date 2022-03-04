package uk.m0nom.location;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.logging.LogManager;

@SpringBootApplication
public class LocationMsApplication {
	static {
		InputStream stream = LocationMsApplication.class.getClassLoader().getResourceAsStream("logging.properties");
		try {
			LogManager.getLogManager().readConfiguration(stream);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				Objects.requireNonNull(stream).close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(LocationMsApplication.class, args);
	}
}
