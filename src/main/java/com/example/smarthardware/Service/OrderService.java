package com.example.smarthardware.Service;

import com.example.smarthardware.Entity.CartItem;
import com.example.smarthardware.Entity.ItemStatus;
import com.example.smarthardware.Entity.Order;
import com.example.smarthardware.Entity.SmartUser;
import com.example.smarthardware.Model.CartItemModel;
import com.example.smarthardware.Model.OrderSummary;
import com.example.smarthardware.Respository.CartItemRepository;
import com.example.smarthardware.Respository.OrderRepository;
import com.example.smarthardware.Respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@PreAuthorize("hasRole('ROLE_CUSTOMER')")
public class OrderService {

    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private CartItemRepository cartItemService;

    public OrderService(OrderRepository orderRepository,UserRepository userRepository,CartItemRepository cartItemService){
        this.orderRepository=orderRepository;
        this.userRepository=userRepository;
        this.cartItemService=cartItemService;
    }


    public OrderSummary checkout(List<CartItem> cartItems){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        SmartUser user=userRepository.findByUserName(auth.getName());
        Order order=new Order();
        order.setCartItems(cartItems);
        order.setSmartUser(user);
        final Order savedOrder=orderRepository.save(order);
        List<CartItem> managedItems=new ArrayList<>();
        for (int i = 0; i <cartItems.size() ; i++) {
            CartItem c=cartItems.get(i);
            c=this.cartItemService.findById(c.getId()).get();
            managedItems.add(c);
        }
        managedItems.forEach(cartItem ->{
            cartItem.setStatus(ItemStatus.PURCHASED);
            cartItem.setOrder(savedOrder);
        });

        this.cartItemService.saveAll(managedItems);
        List<CartItemModel> cartItemModels=CartItemService.cartItemToModel(managedItems);

        OrderSummary orderSummary= new OrderSummary();
        orderSummary.setOrderDate(order.getDateOfPurchase().toString());
        orderSummary.setCartItemModels(cartItemModels);
        float total=0;
        for (CartItemModel c:cartItemModels) {
            total+=Float.parseFloat(c.getPrice());
        }
        orderSummary.setTotal(total
        );
        return orderSummary;

    }
}
