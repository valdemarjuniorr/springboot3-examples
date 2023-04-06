package br.com.valdemarjr.localstack.controller;

import br.com.valdemarjr.localstack.domain.NotificationMessage;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
class NotificationRequest {
  private String from;
  private String to;
  private String content;

  public NotificationMessage toDomain() {
    return new NotificationMessage(this.from, this.to, this.content);
  }
}
