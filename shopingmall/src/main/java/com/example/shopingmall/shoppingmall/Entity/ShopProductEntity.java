package com.example.shopingmall.shoppingmall.Entity;

import com.example.shopingmall.loginwithjwt.JWTutil.Entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class ShopProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Long minPrice;
    private Integer stock; // 재고 수량
    private String status; // 상품 상태

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private ShopEntity shop; // 소속된 쇼핑몰

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserEntity owner; // 상품을 등록한 사업자 사용자
}

