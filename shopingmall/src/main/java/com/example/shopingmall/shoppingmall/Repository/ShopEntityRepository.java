package com.example.shopingmall.shoppingmall.Repository;

import com.example.shopingmall.shoppingmall.Entity.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ShopEntityRepository extends JpaRepository<ShopEntity, Long> {
    // 쇼핑몰 상태와 카테고리를 기준으로 쇼핑몰을 조회하는 메서드
    List<ShopEntity> findByStatusAndCategory(ShopEntity entity, String status, String category);

    // 쇼핑몰 이름으로 검색하는 메서드
    List<ShopEntity> findByNameContaining(String name);

    @Query("SELECT s FROM ShopEntity s WHERE s.status = 'APPLYING'")
    List<ShopEntity> findByApplying();




}
