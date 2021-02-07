package com.orderserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderserver.domain.Order;
import com.orderserver.domain.Product;
import com.orderserver.order.OrderRepository;
import com.orderserver.order.OrderTopic;
import com.orderserver.producer.OrderTopicProducer;
import com.orderserver.product.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderTopicService {

  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  KafkaTemplate<Integer, String> kafkaTemplate;

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private OrderTopicProducer orderTopicProducer;

  public void processOrderTopic(ConsumerRecord<Integer, String> consumerRecord)
      throws JsonProcessingException {
    OrderTopic orderTopic = objectMapper.readValue(consumerRecord.value(), OrderTopic.class);
    log.info("orderTopic : {} ", orderTopic);
    Order order = save(orderTopic);

    // notification topic send
    orderTopic.setOrderId(order.getId());
    orderTopicProducer.sendOrderNotificationTopic(orderTopic);
  }

  private Order save(OrderTopic orderTopic) {
    Order order = new Order();
    Product product = productRepository.findById(Long.valueOf(orderTopic.getProductId()))
        .orElse(null);
    order.setProduct(product);
    order.setQty(orderTopic.getQty());
    order.setProviderId(product.getProvider().getId());
    order.setConfirm(false);

    assert product != null;

    order.setProviderName(product.getProvider().getName());
    log.info("Successfully Persisted the order {}", order);
    return orderRepository.save(order);
  }

}
