package br.com.valdemarjr.observability.controller;

import br.com.valdemarjr.observability.service.ObservabilityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/observability")
public class ObservabilityController {

    private final ObservabilityService service;

    @GetMapping
    public void get() {
        service.get();
        log.info("controller get being observed");
    }

    @PostMapping
    public void post() {
        log.info("post");
    }

    @PutMapping
    public void put() {
        log.info("put");
    }

    @DeleteMapping
    public void delete() {
        log.info("delete");
    }
}
