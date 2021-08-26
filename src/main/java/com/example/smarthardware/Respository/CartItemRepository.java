package com.example.smarthardware.Respository;

import com.example.smarthardware.Entity.CartItem;
import com.example.smarthardware.Entity.ItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    public List<CartItem> findByStatusAndSmartUser_Id(ItemStatus itemStatus,Long id);
}
