package com.example.shopingmall.loginwithjwt.JWTutil.Service;

import com.example.shopingmall.loginwithjwt.JWTutil.Dto.UserDto;
import com.example.shopingmall.loginwithjwt.JWTutil.Entity.UserEntity;
import com.example.shopingmall.loginwithjwt.JWTutil.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class AccountService {

    private final UserRepository userRepository;

    public AccountService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    // 이미지 없이 사용자 정보만 저장하는 경우
    public void saveUserExtraInfo(Long id, UserDto userDto) {
        UserEntity userEntity = userRepository.findById(id).orElse(new UserEntity());
        updateUserEntityWithDto(userEntity, userDto);

        // 변환된 엔터티를 데이터베이스에 저장
        userRepository.save(userEntity);
    }

    public void saveUserExtraInfo(Long id, UserDto userDto, MultipartFile image) throws IOException {
        UserEntity userEntity = userRepository.findById(id).orElse(new UserEntity());
        updateUserEntityWithDto(userEntity, userDto);

        String imagePath = saveImage(image);
        userEntity.setAvatar(imagePath);

        // 변환된 엔터티를 데이터베이스에 저장
        userRepository.save(userEntity);
    }

    private void updateUserEntityWithDto(UserEntity userEntity, UserDto userDto) {
        userEntity.setUsername(userDto.getUsername());
        userEntity.setEmail(userDto.getEmail());
        // 아래 코드는 오류를 포함하고 있습니다. userDto.getAge()가 되어야 할 것 같습니다.
        // userEntity.setAge(userDto.getEmail());
        userEntity.setPhoneNumber(userDto.getPhoneNumber());
        userEntity.setRole(userDto.getRole());
        userEntity.setStatus("Regular"); // 또는 "Normal", 상황에 따라
    }


    private String saveImage(MultipartFile file) throws IOException {

        Path filePath= Paths.get("avartat_srorage", file.getOriginalFilename());

        Files.copy(file.getInputStream(),filePath);

        return filePath.toString(); // 저장된 파일의 경로 반환
    }


    public List<UserEntity> listPreownerUsers() {
        return userRepository.findByStatus("Preowner");
    }

    public void approveOrRejectBusiness(Long userId, boolean approved) {
        userRepository.findById(userId).ifPresent(user -> {
            if (approved) {
                user.setRole("Owner");
            } else {
                user.setRole("General");
            }
            userRepository.save(user);
        });
    }


    public void changeAuthority(UserEntity user){
        String username = user.getUsername();
        userRepository.updateUserStatusByUsername(username, "Owner");
    }
    public void submitBusinessRegistrationNumber(Long userId, String businessRegistrationNumber) {
        // 사용자 조회
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found")); // 사용자가 존재하지 않는 경우 예외 처리

        if (businessRegistrationNumber != null && !businessRegistrationNumber.isEmpty()) {
            // 사업자 등록번호가 유효하다고 가정하고 상태를 PreOwner로 변경
            user.setStatus("PreOwner");
            userRepository.save(user); // 변경된 상태를 저장
        } else {
            // 사업자 등록번호가 유효하지 않은 경우, 에러 처리 또는 로그
            throw new RuntimeException("Invalid business registration number");
        }
    }



}
