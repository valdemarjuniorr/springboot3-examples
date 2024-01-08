package br.com.valdemarjr.localstack.controller;

import br.com.valdemarjr.localstack.domain.NotificationMessage;

record NotificationRequest(String from, String to, String content) {

  public NotificationMessage toDomain() {
    return new NotificationMessage(this.from, this.to, this.content);
  }
}
