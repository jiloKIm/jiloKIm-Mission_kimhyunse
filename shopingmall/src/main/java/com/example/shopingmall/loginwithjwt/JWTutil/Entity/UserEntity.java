package com.example.shopingmall.loginwithjwt.JWTutil.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password; // 해싱된 비밀번호
    private String email;
    private String phoneNumber;
    private Integer age; // 나이
    private String status; // 사용자 상태 (예: 활성, 비활성)
    private String businessRegistrationNumber; // 사업자 등록 번호 (옵션)
    private String avatar; // 프로필 이미지 경로
    private String role; // 사용자 역할 (예: USER, ADMIN)
}

