package br.com.valdemarjr.springsecurityexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestSpringSecurityExampleApplication {

	public static void main(String[] args) {
		SpringApplication.from(SpringSecurityExampleApplication::main).with(TestSpringSecurityExampleApplication.class).run(args);
	}

}
