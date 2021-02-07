package com.orderserver.provider;

import com.orderserver.domain.Provider;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProviderController {

  private final ProviderRepository providerRepository;

  public ProviderController(ProviderRepository providerRepository) {
    this.providerRepository = providerRepository;
  }

  @GetMapping("/v1/stores")
  public ResponseEntity<List<Provider>> postOrderTopic() {
    List<Provider> provider = providerRepository.findAll();

    return ResponseEntity.status(HttpStatus.OK).body(provider);
  }
}
