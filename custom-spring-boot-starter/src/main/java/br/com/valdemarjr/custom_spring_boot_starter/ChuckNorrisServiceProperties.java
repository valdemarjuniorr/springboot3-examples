package br.com.valdemarjr.custom_spring_boot_starter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties("chuck-norris-service")
public record ChuckNorrisServiceProperties(
    @DefaultValue("http://api.chucknorris.io/jokes") String baseUrl) {}
