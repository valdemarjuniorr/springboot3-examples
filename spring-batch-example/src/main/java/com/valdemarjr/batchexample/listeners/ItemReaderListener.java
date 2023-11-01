package com.valdemarjr.batchexample.listeners;

import com.valdemarjr.batchexample.domain.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.annotation.AfterRead;
import org.springframework.batch.core.annotation.BeforeRead;
import org.springframework.batch.core.annotation.OnReadError;

@Slf4j
public class ItemReaderListener {

  @BeforeRead
  public void beforeRead() {
    log.info("Before Read");
  }

  @AfterRead
  public void afterRead(Coffee coffee) {
    log.info("After Read");
  }

  @OnReadError
  public void onReadError(Exception e) {
    log.info("On Read Error");
  }
}
