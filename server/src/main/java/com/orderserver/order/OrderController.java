package com.orderserver.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.orderserver.domain.Order;
import com.orderserver.producer.OrderTopicProducer;
import com.orderserver.provider.ProviderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class OrderController {

  private final ProviderRepository providerRepository;
  private final OrderTopicProducer orderTopicProducer;
  private final OrderRepository orderRepository;

  public OrderController(ProviderRepository providerRepository,
      OrderTopicProducer orderTopicProducer, OrderRepository orderRepository) {
    this.providerRepository = providerRepository;
    this.orderTopicProducer = orderTopicProducer;
    this.orderRepository = orderRepository;
  }

  @PostMapping("/v1/topic/order")
  public ResponseEntity<OrderTopic> postOrderTopic(@RequestBody OrderTopic orderTopic)
      throws JsonProcessingException {
    log.info(orderTopic.toString());
    orderTopicProducer.sendOrderTopic(orderTopic);
    return ResponseEntity.status(HttpStatus.CREATED).body(orderTopic);
  }

  @GetMapping("/v1/{providerId}/orders")
  public ResponseEntity<List<OrderSheet>> getOrders(
      @PathVariable("providerId") Long providerId
  ) {
    List<Order> orders = orderRepository.findByProviderId(providerId);
    List<OrderSheet> orderSheets = new ArrayList<OrderSheet>();
    for (Order order : orders) {
      OrderSheet orderSheet = new OrderSheet();
      orderSheet.setOrderId(order.getId());
      orderSheet.setQty(order.getQty());
      orderSheet.setProductName(order.getProduct().getName());
      orderSheet.setIsConfirm(order.getConfirm());
      orderSheets.add(orderSheet);
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(orderSheets);
  }

  @PutMapping("/v1/order/{orderId}/confirm")
  public Boolean putOrderConfirm(
      @PathVariable("orderId") Long orderId
  ) {
    Order order = orderRepository.findById(orderId).orElse(null);

    assert order != null;

    order.setConfirm(true);
    orderRepository.save(order);
    return true;
  }
}
