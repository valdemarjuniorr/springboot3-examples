package com.valdemarjr.batchexample;

import com.valdemarjr.batchexample.domain.Coffee;
import com.valdemarjr.batchexample.listeners.JobListener;
import com.valdemarjr.batchexample.services.CoffeeService;
import java.io.FileNotFoundException;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j
@SpringBootApplication
public class SpringBatchExampleApplication {

  /** How many records to write on the DB at a time. */
  private static final int CHUNK_SIZE = 100;

  @Autowired private CoffeeService coffeeService;

  public static void main(String[] args) {
    SpringApplication.run(SpringBatchExampleApplication.class, args);
  }

  @Bean
  Job process(JobRepository jobRepository, Step csvToDB) {
    return new JobBuilder("process", jobRepository)
        .listener(new JobListener()) // intercept the job life cycle execution
        .start(csvToDB)
        .build();
  }

  @Bean
  FlatFileItemReader<Coffee> csvRowFlatFileItemReader(@Value("${input.file.name}") String name) {
    return new FlatFileItemReaderBuilder<Coffee>()
        .name("csvRowFlatFileItemReader")
        .resource(new FileSystemResource(name))
        .delimited()
        .delimiter(",")
        .names("review_date,handle,rating,helpfulness_rating,review".split(","))
        .linesToSkip(1)
        .fieldSetMapper(
            fieldSet ->
                new Coffee(
                    fieldSet.readString(0),
                    fieldSet.readString(1),
                    fieldSet.readDouble(2),
                    fieldSet.readString(3),
                    fieldSet.readString(4)))
        .build();
  }

  @Bean
  Step csvToDB(
      JobRepository repository,
      PlatformTransactionManager transactionManager,
      ItemReader csvRowFlatFileItemReader) {
    return new StepBuilder("csvToDB", repository)
        .chunk(CHUNK_SIZE, transactionManager)
        .faultTolerant()
        .noSkip(FileNotFoundException.class)
        .reader(csvRowFlatFileItemReader)
        .writer(
            chunk -> {
              var oneHundredRows = (List<Coffee>) chunk.getItems();
              coffeeService.save(oneHundredRows);
            })
        .build();
  }

  @Bean
  @StepScope
  Tasklet tasklet() {
    return (contribution, chunkContext) -> {
      var value = (String) chunkContext.getStepContext().getJobParameters().get("value");
      log.info(">> " + value);
      return null;
    };
  }

  @Bean
  Step step1(
      JobRepository jobRepository, Tasklet tasklet, PlatformTransactionManager transactionManager) {
    return new StepBuilder("step1", jobRepository).tasklet(tasklet, transactionManager).build();
  }
}
