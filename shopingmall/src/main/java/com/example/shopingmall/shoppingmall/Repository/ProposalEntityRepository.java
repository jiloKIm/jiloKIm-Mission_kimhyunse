package com.example.shopingmall.shoppingmall.Repository;

import com.example.shopingmall.shoppingmall.Entity.PurchaseProposalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ProposalEntityRepository extends JpaRepository<PurchaseProposalEntity, Long> {

    List<PurchaseProposalEntity> findByProductId(Long productId);
}

