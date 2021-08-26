package com.example.smarthardware.Respository;

import com.example.smarthardware.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {

}
