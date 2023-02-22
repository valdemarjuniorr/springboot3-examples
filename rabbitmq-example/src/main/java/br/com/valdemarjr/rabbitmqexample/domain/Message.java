package br.com.valdemarjr.rabbitmqexample.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class Message {
  private String content;
}
