package com.example.shopingmall.jungomarket.Controller;


import com.example.shopingmall.jungomarket.Dto.ProductDto;
import com.example.shopingmall.jungomarket.Dto.PurchaseProposalDto;
import com.example.shopingmall.jungomarket.Entity.PurchaseProposal;
import com.example.shopingmall.jungomarket.Service.ProposalService;
import com.example.shopingmall.loginwithjwt.JWTutil.Entity.CustomUserDetails;
import com.example.shopingmall.loginwithjwt.JWTutil.Entity.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.nio.file.attribute.UserPrincipal;
import java.util.List;

@RestController
@RequestMapping("/jungo/products")

public class PurchaseController {

    private final ProposalService proposalService;

    public PurchaseController(ProposalService proposalService) {
        this.proposalService = proposalService;
    }

    @PostMapping("{productId}/proposals")
    public void proposal(@PathVariable Long productId,@RequestBody PurchaseProposalDto purchaseProposalDto) {
        proposalService.suggestItem(purchaseProposalDto);

    }

    @GetMapping("{productId}/proposals")
    public ResponseEntity<PurchaseProposal> check(@PathVariable Long productId, @RequestBody PurchaseProposalDto purchaseProposalDto,Authentication authentication) throws AccessDeniedException {

        PurchaseProposal dto=proposalService.checkOne(productId,purchaseProposalDto,authentication.getName());

        return ResponseEntity.ok(dto);
    }

    @GetMapping("{productId}/proposals/mine")
    public ResponseEntity<List<PurchaseProposalDto>> getMyProposals(@PathVariable Long productId, Authentication authentication) throws AccessDeniedException {
        // Authentication 객체에서 CustomUserDetails를 가져오고, 이를 통해 현재 로그인한 사용자의 UserEntity를 얻습니다.
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            UserEntity currentUser = userDetails.getUserEntity(); // getUserEntity() 메서드는 CustomUserDetails에서 정의된 메서드입니다.

            Long userId = currentUser.getId(); // 현재 로그인한 사용자의 ID를 얻습니다.

            // ProposalService를 사용하여 현재 사용자의 제안 목록을 조회합니다.
            List<PurchaseProposalDto> myProposals = proposalService.findMyProposals(productId, userId,authentication.getName());

            // 조회된 제안 목록을 ResponseEntity로 감싸서 반환합니다.
            return ResponseEntity.ok(myProposals);
        } else {
            // Authentication 객체에서 CustomUserDetails 타입이 아닌 경우, 적절한 에러 처리를 수행합니다.
            // 예를 들어, 사용자가 인증되지 않은 경우입니다.
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

    }


        @PatchMapping("{productId}/proposals/{proposalId}/accept")
        public void acceptPurchase(@PathVariable Long productId,@PathVariable Long proposalId,Authentication authentication) throws AccessDeniedException {

        proposalService.accept(productId,proposalId,authentication.getName());

        }

    @PatchMapping("{productId}/proposals/{proposalId}/reject")
    public void rejectPurchase(@PathVariable Long productId,@PathVariable Long proposalId,Authentication authentication) throws AccessDeniedException {

        proposalService.reject(productId,proposalId,authentication.getName());



    }

    @PatchMapping("/{productId}/proposals/{proposalId}/confirm")
    public void cofirmPurchase(@PathVariable Long productId,@PathVariable Long proposalId,Authentication authentication) throws AccessDeniedException {

        proposalService.reject(productId,proposalId,authentication.getName());


    }






}
