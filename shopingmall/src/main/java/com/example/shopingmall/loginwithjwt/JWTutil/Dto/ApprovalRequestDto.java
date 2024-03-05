package com.example.shopingmall.loginwithjwt.JWTutil.Dto;

public class ApprovalRequestDto {
    private Long userId;
    private boolean approved;

    // Getter
    public Long getUserId() {
        return userId;
    }

    // Setter
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
