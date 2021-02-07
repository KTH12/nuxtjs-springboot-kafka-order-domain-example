package com.orderserver.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.orderserver.producer.OrderTopicProducer;
import com.orderserver.service.OrderTopicService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderTopicConsumer {

  @Autowired
  private OrderTopicService orderTopicService;


  @KafkaListener(topics = {"order-topics"})
  public void onMessage(ConsumerRecord<Integer, String> consumerRecord)
      throws JsonProcessingException {
    log.info("ConsumerRecord : {} ", consumerRecord);
    orderTopicService.processOrderTopic(consumerRecord);
  }
}
