package br.com.valdemarjr.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ConfigServerExampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(ConfigServerExampleApplication.class, args);
  }
}
