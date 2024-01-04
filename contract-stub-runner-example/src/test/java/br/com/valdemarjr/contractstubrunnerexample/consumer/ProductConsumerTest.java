package br.com.valdemarjr.contractstubrunnerexample.consumer;

import br.com.valdemarjr.contractstubrunnerexample.ProductApplicationServiceTest;
import br.com.valdemarjr.contractstubrunnerexample.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

public class ProductConsumerTest extends ProductApplicationServiceTest {

	private RestTemplate restTemplate = new RestTemplate();

  @Test
  void name() {
	  var response = restTemplate.exchange(
		  "http://localhost:" + port + fraudCheck(), HttpMethod.GET,
		  new HttpEntity<>(request, httpHeaders), Product.class);




  }
}
