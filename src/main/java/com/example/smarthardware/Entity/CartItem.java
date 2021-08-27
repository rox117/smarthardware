package com.example.smarthardware.Entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ItemStatus status;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id",referencedColumnName = "id")
    private Order order;

    @ToString.Exclude
    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private SmartUser smartUser;

    @ToString.Exclude
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    Product product;
}
