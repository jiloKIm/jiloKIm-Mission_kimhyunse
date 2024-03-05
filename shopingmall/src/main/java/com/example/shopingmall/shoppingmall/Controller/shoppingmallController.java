package com.example.shopingmall.shoppingmall.Controller;


import com.example.shopingmall.shoppingmall.Dto.ShopDTO;
import com.example.shopingmall.shoppingmall.Entity.ShopEntity;
import com.example.shopingmall.shoppingmall.Service.ShopService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class shoppingmallController {

    private final ShopService service;

    public shoppingmallController(ShopService service) {
        this.service = service;
    }

    @PostMapping("/shops")
    public ResponseEntity<?> register(ShopDTO shopDTO) {

        service.save(shopDTO);
        if (shopDTO.getName() == null || shopDTO.getDescription() == null || shopDTO.getCategory() == null) {
            return ResponseEntity.badRequest().body("Missing required fields.");
        }

        try {
            service.save(shopDTO);

            return ResponseEntity.ok("Shop registered succesfully");

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error registering shop: " + e.getMessage());
        }


    }

    @PutMapping("/shops/{shopId}")
    public ResponseEntity<?> modify(ShopDTO shopDTO, @PathVariable Long shopId) {

        if (shopDTO.getName() == null || shopDTO.getDescription() == null || shopDTO.getCategory() == null) {
            return ResponseEntity.badRequest().body("Missing required fields.");
        }

        try {
            service.update(shopDTO, shopId);

            return ResponseEntity.ok("Shop registered succesfully");

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error registering shop: " + e.getMessage());
        }

    }

    @GetMapping("/shops/applications")
// 이 메서드가 컨트롤러 내부에 있다고 가정합니다.
    public ResponseEntity<?> checkShops(Authentication authentication) {
        // 현재 인증된 사용자의 이름이 "ADMIN"인지 확인
        if ("ADMIN".equals(authentication.name())) {
            // 쇼핑몰 목록 조회
            List<ShopEntity> shopList = service.check();

            // 조회된 쇼핑몰 목록을 클라이언트에 반환
            return ResponseEntity.ok(shopList);
        } else {
            // "ADMIN" 사용자가 아닌 경우, 적절한 오류 메시지 반환
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
        }
    }

    @PatchMapping("/shops/{shopId}/approve")
    public ResponseEntity<?> approveShops(Authentication authentication, @PathVariable Long shopId) {
        // 현재 인증된 사용자의 이름이 "ADMIN"인지 확인
        if (!"ADMIN".equals(authentication.name())) {

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
        }
        service.approve(shopId);
        return ResponseEntity.ok("Shop approve succesfully");

    }

    @PatchMapping("/shops/{shopId}/reject")
    public ResponseEntity<?> rejectShops(Authentication authentication, @PathVariable Long shopId) {
        // 현재 인증된 사용자의 이름이 "ADMIN"인지 확인
        if (!"ADMIN".equals(authentication.name())) {

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
        }
        service.reject(shopId);
        return ResponseEntity.ok("Shop reject succesfully");

    }




}