package com.example.shopingmall.shoppingmall.Repository;


import com.example.shopingmall.jungomarket.Entity.ProductEntity;
import com.example.shopingmall.shoppingmall.Entity.PurchaseProposalEntity;
import com.example.shopingmall.shoppingmall.Entity.ShopProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductEntityRepository extends JpaRepository<ShopProductEntity, Long> {
    // 특정 쇼핑몰에 속한 상품을 조회하는 메서드
    List<ShopProductEntity> findByShopId(Long shopId);

    // 상품 이름으로 검색하는 메서드
    List<ShopProductEntity> findByTitleContaining(String title);

    // 카테고리와 소분류로 상품을 검색하는 메서드
    List<ShopProductEntity> findByCategoryAndSubcategory(String category, String subcategory);
}
