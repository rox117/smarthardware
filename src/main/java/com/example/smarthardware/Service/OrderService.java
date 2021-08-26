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
        order=orderRepository.save(order);
        cartItems.forEach(cartItem -> cartItem.setStatus(ItemStatus.PURCHASED));
        this.cartItemService.saveAll(cartItems);
        List<CartItemModel> cartItemModels=new ArrayList<>();
        for (CartItem cartItem:cartItems) {
            CartItemModel cartItemModel=new CartItemModel();
            cartItemModel.setCartItemId(cartItem.getId());
            cartItemModel.setProductName(cartItem.getProduct().getName());
            cartItemModel.setPrice(cartItem.getProduct().getPrice());
            cartItemModels.add(cartItemModel);
        }

        OrderSummary orderSummary= new OrderSummary();
        orderSummary.setOrderDate(order.getDateOfPurchase().toString());
        orderSummary.setCartItemModels(cartItemModels);
        return orderSummary;

    }
}
