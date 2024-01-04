package br.com.valdemarjr.contractstubrunnerexample;

import br.com.valdemarjr.contractstubrunnerexample.controllers.ProductController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@AutoConfigureMessageVerifier
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class AbstractMockMvcTest {

  @BeforeEach
  public void setup() {
    var standaloneMockMvcBuilder = MockMvcBuilders.standaloneSetup(new ProductController());
    RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);
  }

}
