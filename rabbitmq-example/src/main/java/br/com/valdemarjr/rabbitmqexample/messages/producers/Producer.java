package br.com.valdemarjr.rabbitmqexample.messages.producers;

import br.com.valdemarjr.rabbitmqexample.domain.Message;

public interface Producer {
  void publish(Message message);
}
