package com.example.shopingmall.loginwithjwt.JWTutil.Controller;

import com.example.shopingmall.loginwithjwt.JWTutil.Dto.ApprovalRequestDto;
import com.example.shopingmall.loginwithjwt.JWTutil.Entity.UserEntity;
import com.example.shopingmall.loginwithjwt.JWTutil.Service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AccountService accountService;

    public AdminController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/preownerUsers")
    public List<UserEntity> listPreownerUsers() {
        return accountService.listPreownerUsers();
    }

    @PostMapping("/approveBusiness")
    public ResponseEntity<?> approveBusiness(@RequestBody List<ApprovalRequestDto> requests) {
        for (ApprovalRequestDto request : requests) {
            accountService.approveOrRejectBusiness(request.getUserId(), request.isApproved());
        }
        return ResponseEntity.ok().body("Processed approval requests.");
    }

    public static class ApprovalRequest {
        private Long userId;
        private boolean approved;

        // Getters and setters
    }
}
