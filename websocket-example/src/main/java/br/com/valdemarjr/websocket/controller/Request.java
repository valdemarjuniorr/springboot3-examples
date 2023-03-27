package br.com.valdemarjr.websocket.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
class Request {
  private String name;
  private String message;
}
