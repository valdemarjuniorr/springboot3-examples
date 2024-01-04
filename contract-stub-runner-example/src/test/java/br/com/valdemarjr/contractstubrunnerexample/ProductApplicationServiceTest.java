package br.com.valdemarjr.contractstubrunnerexample;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(ids = {"br.com.valdemarjr:contract-stub-runner-example:+:stubs:6565"},
	stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class ProductApplicationServiceTest {}
