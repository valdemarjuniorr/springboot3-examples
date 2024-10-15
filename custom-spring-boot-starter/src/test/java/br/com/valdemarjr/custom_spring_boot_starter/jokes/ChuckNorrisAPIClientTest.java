package br.com.valdemarjr.custom_spring_boot_starter.jokes;

import br.com.valdemarjr.custom_spring_boot_starter.ChuckNorrisServiceConfiguration;
import br.com.valdemarjr.custom_spring_boot_starter.ChuckNorrisServiceProperties;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.autoconfigure.web.client.RestClientAutoConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

class ChuckNorrisAPIClientTest {

  private final ApplicationContextRunner contextRunner =
      new ApplicationContextRunner()
          .withConfiguration(
              AutoConfigurations.of(
                  ChuckNorrisServiceConfiguration.class, RestClientAutoConfiguration.class));

  @Test
  void shouldContainChuckNorrisRestClientBean() {
    contextRunner.run(
        context -> {
          Assertions.assertThat(context.containsBean("chuckNorrisAPIClient")).isTrue();
          Assertions.assertThat(context.containsBean("chuckNorrisRestClient")).isTrue();
        });
  }

  @Test
  void shouldContainDefaultBaseUrl() {
    contextRunner.run(
        context -> {
          Assertions.assertThat(context).hasSingleBean(ChuckNorrisServiceProperties.class);
          Assertions.assertThat(context.getBean(ChuckNorrisServiceProperties.class).baseUrl())
              .isEqualTo("http://api.chucknorris.io/jokes");
        });
  }

  @Test
  void shouldGetCategories() {
    contextRunner.run(
        context -> {
          var client = context.getBean(ChuckNorrisAPIClient.class);
          Assertions.assertThat(client.getCategories()).isNotEmpty();
        });
  }

  @Test
  void shouldGetRandomJoke() {
    contextRunner.run(
        context -> {
          var client = context.getBean(ChuckNorrisAPIClient.class);
          Assertions.assertThat(client.getRandomJoke()).isNotNull();
        });
  }
}
