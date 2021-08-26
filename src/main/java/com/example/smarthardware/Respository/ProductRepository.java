package com.example.smarthardware.Respository;

import com.example.smarthardware.Entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
