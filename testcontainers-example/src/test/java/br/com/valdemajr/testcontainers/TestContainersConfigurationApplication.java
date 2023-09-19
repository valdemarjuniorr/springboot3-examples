package br.com.valdemajr.testcontainers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.DockerImageName;

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

	@Container
	static GenericContainer container = new GenericContainer(DockerImageName.parse("myImage/myImage"))
		.withEnv("my.env", "value")
			.withExposedPorts(9999)
			.withNetworkAliases("myNetworkAlias")
			.withCommand("echo Hello World");

	@DynamicPropertySource
	public static void myProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.mail.host", container::getHost);
		registry.add("spring.mail.port", container::getFirstMappedPort);

	}

	@Bean
    @RestartScope
    // springboot-devtools must be in test scope. This annotation will keep the container up and running even if the application restarts
    @ServiceConnection
	GenericContainer myContainer() {
    	return new GenericContainer(DockerImageName.parse("myImage/myImage"))
			.withEnv("my.env", "value")
			.withExposedPorts(9999)
			.withNetworkAliases("myNetworkAlias")
			.withCommand("echo Hello World");
    }
}
