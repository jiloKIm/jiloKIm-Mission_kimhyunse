package com.example.shopingmall.loginwithjwt.JWTutil.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.shopingmall.loginwithjwt.JWTutil.Dto.UserDto;
import com.example.shopingmall.loginwithjwt.JWTutil.Service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@ResponseBody
public class MainController {
    private  final AccountService accountService;

    public MainController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/")
    public String mainP() {

        return "main Controller";

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        String captchaResponse = credentials.get("captchaResponse");

        // NCP CAPTCHA 검증 API URL
        String verifyUrl = "https://localhost:8080/login";

        // API 요청을 위한 파라미터 설정
        Map<String, String> body = new HashMap<>();
        body.put("secret", "YOUR_API_SECRET_KEY"); // API 비밀키
        body.put("response", captchaResponse);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.postForEntity(verifyUrl, body, Map.class);

        Map<String, Object> responseBody = response.getBody();

        if (responseBody != null && responseBody.get("success").equals(true)) {
            // 로그인 성공 로직
            return ResponseEntity.ok().body("로그인 성공");
        } else {
            // 로그인 실패 로직
            return ResponseEntity.badRequest().body("CAPTCHA 인증 실패");
        }
    }

    @PostMapping("/{id}/input")
    public ResponseEntity<String> inputSomething(
            @PathVariable Long id,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @ModelAttribute UserDto userDto) throws IOException {

        // 이미지 파일 처리
        if (image != null && !image.isEmpty()) {
            // 이미지와 사용자 정보를 저장하는 로직
            accountService.saveUserExtraInfo(id,userDto, image);
        } else {
            // 이미지 없이 사용자 정보만 저장하는 경우
            accountService.saveUserExtraInfo(id,userDto);
        }

        return ResponseEntity.ok("정보가 성공적으로 입력되었습니다.");
    }

    @PostMapping("/{id}/submitBusinessRegistration")
    public ResponseEntity<?> submitBusinessRegistration(@PathVariable Long id, @RequestParam String businessRegistrationNumber) {
        try {
            accountService.submitBusinessRegistrationNumber(id, businessRegistrationNumber);
            return ResponseEntity.ok().body("성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("실패 " + e.getMessage());
        }
    }



}