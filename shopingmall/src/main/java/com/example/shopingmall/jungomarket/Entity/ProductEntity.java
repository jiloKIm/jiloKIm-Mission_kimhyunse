package com.example.shopingmall.jungomarket.Entity;

import com.example.shopingmall.loginwithjwt.JWTutil.Entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Long minPrice;
    private String status; // 물품 상태 (예: 판매중, 판매 완료)

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserEntity owner; // 물품을 등록한 사용자

}

