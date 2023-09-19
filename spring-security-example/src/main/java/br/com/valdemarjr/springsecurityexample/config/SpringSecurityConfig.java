package br.com.valdemarjr.springsecurityexample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.List;

@Configuration
@EnableMethodSecurity
public class SpringSecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
    http.httpBasic(Customizer.withDefaults());
	var mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
    http.authorizeHttpRequests(
        requests ->
            requests.requestMatchers(mvcMatcherBuilder.pattern("/users"))
                .access(new WebExpressionAuthorizationManager("isAuthenticated()"))
                .anyRequest()
                .authenticated());

    http.csrf(csrf -> csrf.ignoringRequestMatchers(mvcMatcherBuilder.pattern("/some/endpoint/**")));

    http.cors(
        cors ->
            cors.configurationSource(
                request -> {
                  var conf = new CorsConfiguration();
                  conf.setAllowedMethods(List.of("*"));
                  return conf;
                }));

    return http.build();
  }
}
