package br.com.valdemarjr.rabbitmqexample.controllers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import br.com.valdemarjr.rabbitmqexample.domain.Message;
import br.com.valdemarjr.rabbitmqexample.messages.producers.Producer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RabbitMQControllerTest {

  @Mock private Producer producer;

  @InjectMocks private RabbitMQController controller;

  @Test
  void publish() {
    var request = mock(MessageRequest.class);
    var argCaptor = ArgumentCaptor.forClass(Message.class);

    doNothing().when(producer).publish(argCaptor.capture());

    controller.publish(request);
    verify(producer).publish(argCaptor.getValue());
  }
}
