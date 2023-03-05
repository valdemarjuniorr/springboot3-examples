package br.com.valdemarjr.resilience4j.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/b")
public class ServiceBController {

  @GetMapping
  public String ServiceB() {
    log.info("Service B called");
    return "Service B is called from Service A";
  }

  @GetMapping("/timeout")
  @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
  public void getTimeOut() {
    log.warn("request timeout occurs");
  }

  @GetMapping("/internalerror")
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public void getInternalServerError() {
    log.error("Internal server error occurs");
  }
}
