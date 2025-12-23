package br.com.valdemarjr.datarestexample.projections;

import org.springframework.data.rest.core.config.Projection;

import br.com.valdemarjr.datarestexample.domain.Product;

@Projection(name = "productView", types = Product.class)
public interface ProductView {

  String getName();

  Long getDepartmentId();

  Double getPrice();
}
