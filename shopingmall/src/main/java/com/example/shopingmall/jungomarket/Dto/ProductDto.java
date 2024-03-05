package com.example.shopingmall.jungomarket.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Long id; // 물품 고유 ID
    private String title; // 물품 제목
    private String description; // 물품 설명
    private Long minPrice; // 최소 가격
    private String status; // 물품 상태 (예: 판매중, 판매 완료)
    private String imagePath; // 대표 이미지 경로 (옵션)

    // 물품을 등록한 사용자 정보나, 물품에 대한 추가적인 정보가 필요한 경우 여기에 더 필드를 추가할 수 있습니다.
}

