package com.valdemarjr.batchexample.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequiredArgsConstructor
public class JobLauncherController {

  private final JobLauncher jobLauncher;

  private final Job job;

  @GetMapping("/start")
  public void start() throws Exception {
    log.info("Starting job...");
    var jobParameters =
        new JobParametersBuilder().addLocalDateTime("date", LocalDateTime.now()).toJobParameters();
    jobLauncher.run(job, jobParameters);
    log.info("Job finished...");
  }
}
