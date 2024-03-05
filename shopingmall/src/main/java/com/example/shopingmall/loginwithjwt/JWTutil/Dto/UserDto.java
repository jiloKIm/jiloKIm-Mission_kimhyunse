package com.example.shopingmall.loginwithjwt.JWTutil.Dto;

import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserDto {

    private String username;
    private String password;

    private String email;
    private String phoneNumber;
    private String role;
    private String status;

    private String businessRegistrationNumber;
    private  String age;
    private String avatar;


}
