package br.com.valdemarjr.modulith_example.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

@SpringBootTest
class ModulithExampleApplicationTests {

  @Test
  void contextLoads() {
    var modules = ApplicationModules.of(ModulithExampleApplication.class);
    for (var module : modules) {
      System.out.println(module.getIdentifier() + " : " + module.getBasePackage());
    }
    // Assert if there are any invalid dependencies between modules
    modules.verify();

    // Generate PlantUML diagrams for the modules and theirs dependencies
    new Documenter(modules).writeIndividualModulesAsPlantUml().writeModulesAsPlantUml();
  }
}
