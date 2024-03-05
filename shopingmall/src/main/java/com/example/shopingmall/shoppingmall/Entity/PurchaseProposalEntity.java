package com.example.shopingmall.shoppingmall.Entity;


import com.example.shopingmall.jungomarket.Entity.ProductEntity;
import com.example.shopingmall.loginwithjwt.JWTutil.Entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PurchaseProposalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ShopProductEntity product; // 제안된 상품

    @ManyToOne
    @JoinColumn(name = "proposer_id")
    private UserEntity proposer; // 제안을 한 사용자

    private Long price; // 제안 가격


    private String status; // 제안 상태


}