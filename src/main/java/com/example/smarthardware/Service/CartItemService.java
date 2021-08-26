package com.example.smarthardware.Service;

import com.example.smarthardware.Entity.*;
import com.example.smarthardware.Model.CartItemModel;
import com.example.smarthardware.Respository.CartItemRepository;
import com.example.smarthardware.Respository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@PreAuthorize("hasRole('ROLE_CUSTOMER')")
public class CartItemService {
    private CartItemRepository cartItemRepository;
    private UserRepository userRepository;
    private ProductService productService;

    public CartItemService(CartItemRepository cartItemRepository,UserRepository userRepository,ProductService productService){
        this.cartItemRepository=cartItemRepository;
        this.userRepository=userRepository;
        this.productService=productService;
    }

    public CartItemModel addToCart(Long productId){
        Product product=productService.findbyid(productId);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        SmartUser user=userRepository.findByUserName(auth.getName());
        CartItem cartItem=new CartItem();
        cartItem.setProduct(product);
        cartItem.setSmartUser(user);
        cartItem.setStatus(ItemStatus.INCART);
        CartItemModel cartItemModel=new CartItemModel();
        cartItem=this.cartItemRepository.save(cartItem);
        cartItemModel.setCartItemId(cartItem.getId());
        cartItemModel.setProductName(cartItem.getProduct().getName());
        cartItemModel.setPrice(cartItem.getProduct().getPrice());
        return cartItemModel;
    }

    public void deleteCartItem(Long cartItemID){
        CartItem cartItem= this.cartItemRepository.findById(cartItemID).get();
        this.cartItemRepository.delete(cartItem);

    }

    public List<CartItemModel> findItemsforUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        SmartUser user=userRepository.findByUserName(auth.getName());
        List<CartItem> cartItems= cartItemRepository.findByStatusAndSmartUser_Id(ItemStatus.INCART,user.getId());
        List<CartItemModel> cartItemModels=new ArrayList<>();
        for (CartItem cartItem:cartItems) {
            CartItemModel cartItemModel=new CartItemModel();
            cartItemModel.setCartItemId(cartItem.getId());
            cartItemModel.setProductName(cartItem.getProduct().getName());
            cartItemModel.setPrice(cartItem.getProduct().getPrice());
            cartItemModels.add(cartItemModel);
        }

        return cartItemModels;


    }



}
