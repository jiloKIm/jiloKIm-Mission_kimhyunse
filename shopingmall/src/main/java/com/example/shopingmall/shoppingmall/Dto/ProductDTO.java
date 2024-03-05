package com.example.shopingmall.shoppingmall.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private Long id; // 상품 ID
    private String title; // 상품 제목
    private String description; // 상품 설명
    private Long minPrice; // 최소 가격
    private String category; // 상품 분류
    private String subCategory; // 상품 소분류
    private Integer stock; // 재고 수량

    // 생성자, getter, setter 메서드는 Lombok 어노테이션을 통해 자동 생성됩니다.
    // 필요에 따라 추가적인 필드나 메서드를 포함시킬 수 있습니다.
}
