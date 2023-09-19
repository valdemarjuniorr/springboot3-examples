package com.valdemarjr.batchexample;

import com.valdemarjr.batchexample.domain.Coffee;
import com.valdemarjr.batchexample.domain.CoffeeRecord;
import com.valdemarjr.batchexample.listeners.JobListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringBatchExampleApplication {

  /** How many records to write on the DB at a time. */
  private static final int CHUNK_SIZE = 100;

  public static void main(String[] args) {
    SpringApplication.run(SpringBatchExampleApplication.class, args);
  }

  @Bean
  Job process(JobRepository jobRepository, Step csvToDB) {
    return new JobBuilder("process", jobRepository)
        .preventRestart()
        .listener(new JobListener()) // intercept the job life cycle execution
        .start(csvToDB)
		.next(csvToDB)
        .build();
  }

  @Bean
  FlatFileItemReader<Coffee> csvRowFlatFileItemReader(Resource resource) {
    return new FlatFileItemReaderBuilder<Coffee>()
        .name("csvRowFlatFileItemReader")
        .resource(resource)
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
  Step csvToDB(JobRepository repository, PlatformTransactionManager transactionManager) {
    return new StepBuilder("csvToDB", repository)
        .chunk(CHUNK_SIZE, transactionManager)
		.faultTolerant()
		.skip(Exception.class)
		.noSkip(FileNotFoundException.class)
        .reader(
            new ItemReader<Object>() {

              @Override
              public Object read()
                  throws Exception,
                      UnexpectedInputException,
                      ParseException,
                      NonTransientResourceException {
                return null;
              }
            })
        .writer(
            new ItemWriter<Object>() {

              @Override
              public void write(Chunk<?> chunk) throws Exception {
                var oneHundredRows = chunk.getItems();
                System.out.println(oneHundredRows);
              }
            })
        .build();
  }

	/**
	 * In Spring Batch 4, the CompositeItemWriter implements ItemStream so this isn't
	 * necessary, but used for an example.
	 */
	@Bean
	public CompositeItemWriter compositeItemWriter() {
		var writers = new ArrayList<>(2);
//		writers.add(fileItemWriter1());
//		writers.add(fileItemWriter2());

		var itemWriter = new CompositeItemWriter();

		itemWriter.setDelegates(writers);

		return itemWriter;
	}

  @Bean
  @StepScope
  Tasklet tasklet() {
    return (contribution, chunkContext) -> {
      var value = (String) chunkContext.getStepContext().getJobParameters().get("value");
      System.out.println(">> " + value);
      return null;
    };
  }

  @Bean
  Step step1(
      JobRepository jobRepository, Tasklet tasklet, PlatformTransactionManager transactionManager) {
    return new StepBuilder("step1", jobRepository)
        .tasklet(tasklet, transactionManager)
        //		.<String, String>chunk(CHUNK_SIZE, transactionManager)
        //		.writer(new Writer())
        .build();
  }
}
