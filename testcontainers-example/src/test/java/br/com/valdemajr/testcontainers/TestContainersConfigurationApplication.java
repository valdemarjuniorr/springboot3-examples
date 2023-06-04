package br.com.valdemajr.testcontainers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;

@Configuration
public class TestContainersConfigurationApplication {

	@Bean
	@RestartScope // spring-boot-devtools must be in test scope. This annotation will keep the container up and running even if the application restart
	@ServiceConnection
	PostgreSQLContainer postgresSQLContainer() {
		return new PostgreSQLContainer("postgres");
	}

	public static void main(String[] args) {
		SpringApplication.from(TestcontainersExampleApplication::main)
			.with(TestContainersConfigurationApplication.class)
			.run(args);
	}
}
