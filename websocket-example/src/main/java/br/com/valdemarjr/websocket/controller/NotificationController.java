package br.com.valdemarjr.websocket.controller;

import br.com.valdemarjr.websocket.message.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class NotificationController {

  @MessageMapping("/topic")
  @SendTo("/notify/message")
  public Message notify(Request request) {
    log.info("Incoming request {}", request);
    return new Message(request.getName(), request.getMessage());
  }
}
