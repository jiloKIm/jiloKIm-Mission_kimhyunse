package com.example.shopingmall.jungomarket.repo;

import com.example.shopingmall.jungomarket.Entity.ProductEntity;
import com.example.shopingmall.loginwithjwt.JWTutil.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>{


    @Query("SELECT p.description FROM ProductEntity p")
    List<String> findAllDescriptions();

    ProductEntity findByOwner(UserEntity owner);
}
