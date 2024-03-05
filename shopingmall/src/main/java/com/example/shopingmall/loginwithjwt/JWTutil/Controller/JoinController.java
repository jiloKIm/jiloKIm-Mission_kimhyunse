package com.example.shopingmall.loginwithjwt.JWTutil.Controller;

import com.example.shopingmall.loginwithjwt.JWTutil.Dto.JoinDto;
import com.example.shopingmall.loginwithjwt.JWTutil.Service.JoinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@Slf4j
public class JoinController {

    private final JoinService joinService;

    public JoinController(JoinService joinService) {

        this.joinService = joinService;
    }

    @PostMapping("/join")
    public String joinProcess(JoinDto joinDTO) {
        log.info(joinDTO.getUsername());
        joinService.joinProcess(joinDTO);

        return "ok";
    }
}

