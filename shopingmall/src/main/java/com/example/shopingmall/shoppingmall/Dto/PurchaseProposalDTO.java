package com.example.shopingmall.shoppingmall.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseProposalDTO {
    private Long productId; // 제안된 상품 ID
    private Long proposerId; // 제안을 한 사용자 ID
    private String status; // 제안 상태 ("PENDING", "ACCEPTED", "REJECTED")

    // 필요에 따라 추가적인 필드나 메서드를 포함시킬 수 있습니다.
}
