package com.example.smarthardware.Controller;


import com.example.smarthardware.Entity.CartItem;
import com.example.smarthardware.Entity.Order;
import com.example.smarthardware.Entity.Product;
import com.example.smarthardware.Model.CartItemModel;
import com.example.smarthardware.Model.OrderSummary;
import com.example.smarthardware.Service.CartItemService;
import com.example.smarthardware.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cartitem")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private OrderService orderService;

    @GetMapping(path = "/")
    public ResponseEntity<List<CartItemModel>> getCart(){
        List<CartItemModel> products=this.cartItemService.findItemsforUser();
        return ResponseEntity.ok(products);

    }

    @PostMapping(path = "/{productId}")
    public ResponseEntity<CartItemModel> create(@PathVariable Long productId){
        CartItemModel cartItem=this.cartItemService.addToCart(productId);
        return ResponseEntity.ok(cartItem);
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        this.cartItemService.deleteCartItem(id);
        return ResponseEntity.ok("product deleted");
    }

    @PostMapping(path = "/checkout}")
    public ResponseEntity<OrderSummary> checkout(@RequestBody List <CartItem> cartItems){
        OrderSummary order=this.orderService.checkout(cartItems);

        return ResponseEntity.ok(order);
    }


}
