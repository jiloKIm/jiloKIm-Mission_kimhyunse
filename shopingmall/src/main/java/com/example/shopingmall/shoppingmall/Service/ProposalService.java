package com.example.shopingmall.shoppingmall.Service;


import com.example.shopingmall.jungomarket.Entity.ProductEntity;
import com.example.shopingmall.loginwithjwt.JWTutil.Entity.UserEntity;
import com.example.shopingmall.loginwithjwt.JWTutil.Repository.UserRepository;
import com.example.shopingmall.shoppingmall.Entity.PurchaseProposalEntity;
import com.example.shopingmall.shoppingmall.Entity.ShopProductEntity;
import com.example.shopingmall.shoppingmall.Repository.ProductEntityRepository;
import com.example.shopingmall.shoppingmall.Repository.ProposalEntityRepository;
import com.example.shopingmall.shoppingmall.Repository.ShopEntityRepository;
import jakarta.transaction.Transactional;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProposalService {

    private final ProductEntityRepository productEntityRepository;
    private final ProposalEntityRepository proposalEntityRepository;
    private final UserRepository userRepository; // UserRepository 추가

    public ProposalService(ProductEntityRepository productEntityRepository,
                           ProposalEntityRepository proposalEntityRepository,
                           UserRepository userRepository) {
        this.productEntityRepository = productEntityRepository;
        this.proposalEntityRepository = proposalEntityRepository;
        this.userRepository = userRepository;
    }

    public void add(Long productId, String status, Authentication authentication) {
        // 상품과 사용자(제안자)를 찾습니다.

        ShopProductEntity product = productEntityRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product not found"));

        UserEntity proposer = userRepository.findByUsername(authentication.name());

        // 새로운 구매 제안을 생성합니다.
        PurchaseProposalEntity proposal = new PurchaseProposalEntity();
        proposal.setProduct(product);
        proposal.setProposer(proposer);
        proposal.setStatus(status); // Enum 사용
        proposal.setPrice(0L); // 가격 설정이 필요하다면 추가합니다.

        // 구매 제안을 저장합니다.
        proposalEntityRepository.save(proposal);

    }

    public List<PurchaseProposalEntity> check(Long productId, String name) {
        // 상품 소유자를 확인합니다.
        ShopProductEntity product = productEntityRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        if (!product.getOwner().getUsername().equals(name)) {
            throw new IllegalStateException("User is not the owner of the product");
        }

        // 해당 상품에 대한 모든 구매 제안을 반환합니다.
        return proposalEntityRepository.findByProductId(productId);
    }
    @Transactional
    public void acceptProposal(Long proposalId) {
        PurchaseProposalEntity proposal = proposalEntityRepository.findById(proposalId)
                .orElseThrow(() -> new IllegalStateException("Proposal not found"));
        proposal.setStatus("승인");
        proposalEntityRepository.save(proposal);
    }

    // 구매 제안을 거절하는 메소드
    @Transactional
    public void rejectProposal(Long proposalId) {
        PurchaseProposalEntity proposal = proposalEntityRepository.findById(proposalId)
                .orElseThrow(() -> new IllegalStateException("Proposal not found"));
        proposal.setStatus("불가");
        proposalEntityRepository.save(proposal);
    }
}

