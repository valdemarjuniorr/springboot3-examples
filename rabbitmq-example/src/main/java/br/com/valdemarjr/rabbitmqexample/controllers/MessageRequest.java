package br.com.valdemarjr.rabbitmqexample.controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
class MessageRequest {
  private String content;
}
