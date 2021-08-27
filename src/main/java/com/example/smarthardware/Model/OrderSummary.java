package com.example.smarthardware.Model;

import lombok.Data;

import java.util.List;

@Data
public class OrderSummary {
    private String orderDate;
    private List<CartItemModel> cartItemModels;
    private float total;



}
