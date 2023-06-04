package br.com.valdemajr.testcontainers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;

/**
 * Test application to integrate with TestContainers for local development
 */
@Configuration
public class TestContainersConfigurationApplication {

    public static void main(String[] args) {
        SpringApplication.from(TestcontainersExampleApplication::main)
                .with(TestContainersConfigurationApplication.class)
                .run(args);
    }

    @Bean
    @RestartScope
    // springboot-devtools must be in test scope. This annotation will keep the container up and running even if the application restarts
    @ServiceConnection
    PostgreSQLContainer postgresSQLContainer() {
        return new PostgreSQLContainer("postgres");
    }
}
