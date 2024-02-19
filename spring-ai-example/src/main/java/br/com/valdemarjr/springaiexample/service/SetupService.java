package br.com.valdemarjr.springaiexample.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Service responsible for deleting an existing database and initializing a new one with the data
 * from the PDF file medicaid-wa-faqs.pdf.
 */
@Service
public class SetupService {

  private static final Logger log = LoggerFactory.getLogger(SetupService.class);

  @Value("classpath:medicaid-wa-faqs.pdf")
  private Resource pdf;

  private final JdbcTemplate jdbcTemplate;
  private final VectorStore vectorStore;

  public SetupService(JdbcTemplate jdbcTemplate, VectorStore vectorStore) {
    this.jdbcTemplate = jdbcTemplate;
    this.vectorStore = vectorStore;
  }

  public void init() {
    // delete an existent database
    jdbcTemplate.update("delete from vector_store");

	// initialize a new database with the data from the PDF file
    var config =
        PdfDocumentReaderConfig.builder()
            .withPageExtractedTextFormatter(
                new ExtractedTextFormatter.Builder().withNumberOfBottomTextLinesToDelete(3).build())
            .build();

    var pdfReader = new PagePdfDocumentReader(pdf, config);

    var textSplitter = new TokenTextSplitter();
    var docs = textSplitter.apply(pdfReader.get());

	// store the data in the vector store
    vectorStore.accept(docs);
    log.info("Vector store finished");
  }
}
