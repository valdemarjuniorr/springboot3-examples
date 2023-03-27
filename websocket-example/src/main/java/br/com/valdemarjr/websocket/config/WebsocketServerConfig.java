package br.com.valdemarjr.websocket.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class WebsocketServerConfig implements WebSocketMessageBrokerConfigurer {

  private final WebsocketConfig config;

  @Override
  public void configureMessageBroker(MessageBrokerRegistry registry) {
    registry.enableSimpleBroker(config.getBrokerPath());
    // We enable an in-memory message broker to carry the messages back to the client on
    // destinations prefixed with “/topic”.
    registry.setApplicationDestinationPrefixes(config.getDestinationPrefixes());
  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint(config.getStompEndpoint()).withSockJS();
  }
}
