package br.com.valdemarjr.web.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  @PreAuthorize("hasAuthority('SCOPE_user.read')")
  public String getProducts() {
    return "Hey " + getCurrentUser() + ", here are the product";
  }

  private String getCurrentUser() {
    var jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return jwt.getSubject();
  }
}
