package br.com.valdemarjr.springaiexample;

import br.com.valdemarjr.springaiexample.service.SetupService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringAiExampleApplication {

  private final SetupService setupService;

  public SpringAiExampleApplication(SetupService setupService) {
    this.setupService = setupService;
  }

  @Bean
  ApplicationRunner applicationRunner() {
    return _ -> setupService.init();
  }

  public static void main(String[] args) {
    SpringApplication.run(SpringAiExampleApplication.class, args);
  }
}