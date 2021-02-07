package com.orderserver.order;

import com.orderserver.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByProviderId(Long providerId);

}
