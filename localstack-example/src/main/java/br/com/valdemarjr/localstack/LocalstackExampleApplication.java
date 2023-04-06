package br.com.valdemarjr.localstack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@AutoConfiguration
@SpringBootApplication
public class LocalstackExampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(LocalstackExampleApplication.class, args);
  }

}
