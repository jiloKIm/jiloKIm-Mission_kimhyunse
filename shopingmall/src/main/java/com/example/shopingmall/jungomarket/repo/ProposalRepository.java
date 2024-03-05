package com.example.shopingmall.jungomarket.repo;

import com.example.shopingmall.jungomarket.Entity.ProductEntity;
import com.example.shopingmall.jungomarket.Entity.PurchaseProposal;
import com.example.shopingmall.loginwithjwt.JWTutil.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProposalRepository extends JpaRepository<PurchaseProposal, Long> {

    @Query("SELECT p FROM PurchaseProposal p WHERE p.product = :product AND p.proposer = :proposer")
    Optional<PurchaseProposal> findByProcuctWhereUser(@Param("product") ProductEntity product, @Param("proposer") UserEntity proposer);

    @Query("SELECT pp FROM PurchaseProposal pp WHERE pp.product.id = :productId AND pp.proposer.id = :userId")
    List<PurchaseProposal> findByProductIdAndUserId(@Param("productId") Long productId, @Param("userId") Long userId);

    @Query("SELECT p FROM PurchaseProposal p WHERE p.product = :product AND p.proposer = :proposer")
    Optional<PurchaseProposal> findByProcuctWhereOwner(@Param("product") ProductEntity product, @Param("proposer") UserEntity proposer);


    List<PurchaseProposal> findByProductId(Long productId);
}
