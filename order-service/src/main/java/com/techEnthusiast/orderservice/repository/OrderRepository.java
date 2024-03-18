package com.techEnthusiast.orderservice.repository;

import com.techEnthusiast.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
