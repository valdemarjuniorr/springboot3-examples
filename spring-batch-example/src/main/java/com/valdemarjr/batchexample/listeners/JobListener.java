package com.valdemarjr.batchexample.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
* Class responsible for listening to the job life cycle execution.
*/
@Slf4j
public class JobListener implements JobExecutionListener {

  @Override
  public void beforeJob(JobExecution jobExecution) {
    log.info("Before Job");
  }

  @Override
  public void afterJob(JobExecution jobExecution) {
    log.info("After Job");
  }
}
