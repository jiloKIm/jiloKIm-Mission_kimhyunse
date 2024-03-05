package com.example.shopingmall.loginwithjwt.JWTutil.Service;

import com.example.shopingmall.loginwithjwt.JWTutil.Entity.UserEntity;
import com.example.shopingmall.loginwithjwt.JWTutil.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> listPreownerUsers() {
        return userRepository.findByStatus("Preowner");
    }

    public void approveOrRejectBusiness(Long userId, boolean approved) {
        userRepository.findById(userId).ifPresent(user -> {
            if (approved) {
                user.setStatus("Owner");
            } else {
                user.setStatus("General");
            }
            userRepository.save(user);
        });
    }

}

