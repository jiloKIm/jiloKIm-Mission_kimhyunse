package com.example.shopingmall.jungomarket.Entity;


import com.example.shopingmall.loginwithjwt.JWTutil.Entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PurchaseProposal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product; // 제안된 물품

    @ManyToOne
    @JoinColumn(name = "proposer_id")
    private UserEntity proposer; // 제안을 한 사용자

    private String status; // 제안 상태 (예: 수락, 거절, 확정)
    private Long price; // 제안 가격
}

