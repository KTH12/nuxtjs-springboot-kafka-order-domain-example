package com.orderserver.product;

import com.orderserver.domain.Product;
import com.orderserver.domain.Provider;
import com.orderserver.provider.ProviderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ProductController {

  private final ProviderRepository providerRepository;
  private final ProductRepository productRepository;

  public ProductController(ProductRepository productRepository,
      ProviderRepository providerRepository) {
    this.productRepository = productRepository;
    this.providerRepository = providerRepository;
  }


  @GetMapping("/v1/{providerId}/products")
  public ResponseEntity<List<Product>> postOrderTopic(
      @PathVariable("providerId") Integer providerId
  ) {
    Provider provider = providerRepository.findById(Long.valueOf(providerId)).orElse(null);

    assert provider != null;

    List<Product> products = provider.getProducts();
    return ResponseEntity.status(HttpStatus.CREATED).body(products);
  }
}
