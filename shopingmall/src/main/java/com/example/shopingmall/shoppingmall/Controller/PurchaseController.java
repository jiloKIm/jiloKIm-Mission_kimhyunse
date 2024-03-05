package com.example.shopingmall.shoppingmall.Controller;

import com.example.shopingmall.shoppingmall.Entity.PurchaseProposalEntity;
import com.example.shopingmall.shoppingmall.Service.ProposalService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PurchaseController {

    private final ProposalService proposalService;

    public PurchaseController(ProposalService proposalService) {
        this.proposalService = proposalService;
    }

    @PostMapping("/products/{productId}/proposals")
    public ResponseEntity<?> proposal(@PathVariable Long productId, @RequestParam String status, Authentication authentication){
        try {
            proposalService.add(productId, status, authentication);
            return ResponseEntity.ok().body("Proposal added successfully");
        } catch (Exception e) {
            // 실패한 경우에 대한 처리
            return ResponseEntity.badRequest().body("Failed to add proposal: " + e.getMessage());
        }
    }

    @GetMapping("/products/{productId}/proposals")
    public ResponseEntity<?> getProposal(@PathVariable Long productId, Authentication authentication){
        try {
            List<PurchaseProposalEntity> proposals = proposalService.check(productId, authentication.name());
            return ResponseEntity.ok(proposals); // 조회된 제안 목록 반환
        } catch (Exception e) {
            // 실패한 경우에 대한 처리
            return ResponseEntity.badRequest().body("Failed to retrieve proposals: " + e.getMessage());
        }
    }
    // 구매 제안을 수락하는 엔드포인트
    @PatchMapping("/products/{proposalId}/accept")
    public ResponseEntity<?> acceptProposal(@PathVariable Long proposalId, Authentication authentication) {
        // 여기에서 상품 소유자 인증을 추가할 수 있습니다.
        proposalService.acceptProposal(proposalId);
        return ResponseEntity.ok().body("Proposal accepted successfully");
    }

    // 구매 제안을 거절하는 엔드포인트
    @PatchMapping("/products/{proposalId}/reject")
    public ResponseEntity<?> rejectProposal(@PathVariable Long proposalId, Authentication authentication) {
        // 여기에서 상품 소유자 인증을 추가할 수 있습니다.
        proposalService.rejectProposal(proposalId);
        return ResponseEntity.ok().body("Proposal rejected successfully");
    }




}
