package com.example.shopingmall.loginwithjwt.JWTutil.Service;


import com.example.shopingmall.loginwithjwt.JWTutil.Entity.CustomUserDetails;
import com.example.shopingmall.loginwithjwt.JWTutil.Entity.UserEntity;
import com.example.shopingmall.loginwithjwt.JWTutil.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


// 유저ㅗ 디테일은 스프링 시큐리티에서 사용자의 정보를 담는 인터페이스이다. 이 과정에서 인터페이스를 만들고 어ㄸ떤권한을 가지고 있는지 확인한다.
//유저디테일은 스프링 시큐리티에 이미 있기에 이를 사용한다.


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userData = userRepository.findByUsername(username);

        if (userData != null) {

            return new CustomUserDetails(userData);
        }


        return null;
    }
}
