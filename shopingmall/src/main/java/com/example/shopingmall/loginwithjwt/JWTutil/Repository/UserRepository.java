package com.example.shopingmall.loginwithjwt.JWTutil.Repository;

import com.example.shopingmall.loginwithjwt.JWTutil.Entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Boolean existsByUsername(String username);

    UserEntity findByUsername(String username);

    // 사용자 상태를 업데이트하는 메소드
    @Modifying
    @Transactional
    @Query("UPDATE UserEntity u SET u.status = :status WHERE u.username = :username")
    void updateUserStatusByUsername(String username, String status);

    // 'PreOwner' 상태의 모든 사용자를 조회하는 메소드

    List<UserEntity> findByStatus(String status);


}


