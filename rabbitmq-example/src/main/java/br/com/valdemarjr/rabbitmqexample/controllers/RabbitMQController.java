package br.com.valdemarjr.rabbitmqexample.controllers;

import br.com.valdemarjr.rabbitmqexample.domain.Message;
import br.com.valdemarjr.rabbitmqexample.messages.producers.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/publish")
public class RabbitMQController {

  private final Producer producer;

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  public void publish(@RequestBody MessageRequest request) {
    producer.publish(new Message(request.getContent()));
  }
}
