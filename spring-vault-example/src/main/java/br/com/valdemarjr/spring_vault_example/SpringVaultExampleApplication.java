package br.com.valdemarjr.spring_vault_example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

@SpringBootApplication
public class SpringVaultExampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringVaultExampleApplication.class, args);
  }

  @RestController
  class VaultController {

    @Value("${database.username}")
    private String userName;

    @Value("${database.password}")
    private String password;

    @GetMapping("/")
    String getValuesFromVault() {
      return "Those values are from Vault: " + userName + ":" + password;
    }
  }
}
