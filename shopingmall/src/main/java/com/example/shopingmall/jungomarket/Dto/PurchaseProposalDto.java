package com.example.shopingmall.jungomarket.Dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PurchaseProposalDto {
    private Long id; // 구매 제안의 ID
    private Long productId; // 제안된 물품의 ID
    private Integer proposerId; // 제안자의 ID
    private String status; // 제안의 상태 (예: 수락, 거절, 확정)
    private Long price; // 제안 가격

    // 기본 생성자, 전체 인자를 받는 생성자, getter 및 setter
    public PurchaseProposalDto() {
    }

    public PurchaseProposalDto(Long id, Long productId, Integer proposerId, String status, Long price) {
        this.id = id;
        this.productId = productId;
        this.proposerId = proposerId;
        this.status = status;
        this.price = price;
    }

}
