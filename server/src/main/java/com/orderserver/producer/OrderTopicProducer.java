package com.orderserver.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderserver.order.OrderTopic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@Slf4j
public class OrderTopicProducer {

  final
  KafkaTemplate<Integer, String> kafkaTemplate;
  final
  ObjectMapper objectMapper;

  String topic = "event-topics";

  public OrderTopicProducer(KafkaTemplate<Integer, String> kafkaTemplate,
      ObjectMapper objectMapper) {
    this.kafkaTemplate = kafkaTemplate;
    this.objectMapper = objectMapper;
  }

  public void sendOrderTopic(OrderTopic orderTopic) throws JsonProcessingException {
    String value = objectMapper.writeValueAsString(orderTopic);

    ListenableFuture<SendResult<Integer, String>> listenableFuture = kafkaTemplate
        .send("order-topics", value);
    listenableFuture.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
      @Override
      public void onFailure(Throwable ex) {
        handleFailure(null, value, ex);
      }

      @Override
      public void onSuccess(SendResult<Integer, String> result) {
        handleSuccess(null, value, result);
      }
    });
  }

  public void sendOrderNotificationTopic(OrderTopic orderTopic) throws JsonProcessingException {
    String value = objectMapper.writeValueAsString(orderTopic);

    ListenableFuture<SendResult<Integer, String>> listenableFuture = kafkaTemplate
        .send("order-notification-topic", value);
    listenableFuture.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {
      @Override
      public void onFailure(Throwable ex) {
        handleFailure(null, value, ex);
      }

      @Override
      public void onSuccess(SendResult<Integer, String> result) {
        handleSuccess(null, value, result);
      }
    });
  }

  private void handleFailure(Integer key, String value, Throwable ex) {
    log.error("Error Sending Message and is exception is {}", ex.getMessage());
    try {
      throw ex;
    } catch (Throwable throwable) {
      log.error("Error in onFailure {}", throwable.getMessage());
    }
  }

  private void handleSuccess(Integer key, String value, SendResult<Integer, String> result) {
    log.info("Success for the key : {}, value is {}, partition is {}", key, value,
        result.getProducerRecord().partition());
  }
}
