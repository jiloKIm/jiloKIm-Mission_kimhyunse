package com.example.shopingmall.shoppingmall.Entity;

import com.example.shopingmall.jungomarket.Entity.ProductEntity;
import com.example.shopingmall.loginwithjwt.JWTutil.Entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class ShopEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String category; // 쇼핑몰 분류
    private String status; // 쇼핑몰 상태

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserEntity owner; // 쇼핑몰 주인

    @OneToMany(mappedBy = "shop")
    private Set<ShopProductEntity> products = new HashSet<>();


}
