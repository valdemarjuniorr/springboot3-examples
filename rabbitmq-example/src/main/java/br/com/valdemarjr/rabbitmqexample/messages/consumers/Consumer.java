package br.com.valdemarjr.rabbitmqexample.messages.consumers;

import br.com.valdemarjr.rabbitmqexample.domain.Message;

public interface Consumer {
    void consume(Message message);
}
