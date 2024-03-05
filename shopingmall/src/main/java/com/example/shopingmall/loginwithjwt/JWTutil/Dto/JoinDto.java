package com.example.shopingmall.loginwithjwt.JWTutil.Dto;

import lombok.Data;
import lombok.Getter;

@Data
public class JoinDto {
    private String username;
    private String password;
    private  String role="비활성사용자";

}
