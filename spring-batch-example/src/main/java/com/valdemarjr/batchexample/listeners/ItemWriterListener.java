package com.valdemarjr.batchexample.listeners;

import com.valdemarjr.batchexample.domain.Coffee;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.annotation.AfterWrite;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.core.annotation.OnWriteError;

@Slf4j
public class ItemWriterListener {

  @BeforeWrite
  public void beforeWrite(List<Coffee> items) {
    log.info("Before Write");
  }

  @AfterWrite
  public void afterWrite(List<Coffee> items) {
    log.info("After Write");
  }

  @OnWriteError
  public void onWriteError(Exception exception, List<Coffee> items) {
    log.info("On Write Error");
  }
}
