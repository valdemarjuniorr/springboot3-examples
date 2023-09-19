package com.valdemarjr.batchexample.listeners;

import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.AfterChunkError;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;

/**
 * This interface is used to intercept the step execution life cycle. Reference
 * https://docs.spring.io/spring-batch/docs/current/reference/html/index-single.html#chunkListener
 */
public class ChunkStepListener {

  @BeforeChunk
  public void beforeChunk(ChunkContext context) {}

  @AfterChunk
  public void afterChunk(ChunkContext context) {}

  @AfterChunkError
  public void afterChunkError(ChunkContext context) {}
}
