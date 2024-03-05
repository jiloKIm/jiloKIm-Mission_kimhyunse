package com.example.shopingmall.shoppingmall.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopDTO {
    private Long id;
    private String name;
    private String description;
    private String category; // 쇼핑몰 분류
    private String status; // 쇼핑몰 상태 (문자열 형태로 처리)
    private Long ownerId; // 쇼핑몰 주인의 ID

    // 상품 목록은 필요에 따라 DTO에 포함시킬 수 있습니다.
    // 이 예에서는 간단히 하기 위해 생략합니다.
    // private Set<ProductDTO> products;

    // ShopDTO 생성자, getter, setter는 Lombok 어노테이션을 통해 자동 생성됩니다.
}
