package com.example.shopingmall.shoppingmall.Service;

import com.example.shopingmall.loginwithjwt.JWTutil.Entity.UserEntity;
import com.example.shopingmall.loginwithjwt.JWTutil.Repository.UserRepository;
import com.example.shopingmall.shoppingmall.Dto.ProductDTO;
import com.example.shopingmall.shoppingmall.Dto.ShopDTO;
import com.example.shopingmall.shoppingmall.Entity.ShopEntity;
import com.example.shopingmall.shoppingmall.Repository.ShopEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ShopService {

    private final ShopEntityRepository shopEntityRepository;

    @Autowired
    private final UserRepository userRepository;




    public ShopService(ShopEntityRepository shopEntityRepository, UserRepository userRepository) {
        this.shopEntityRepository = shopEntityRepository;
        this.userRepository = userRepository;
    }

    public void save(ShopDTO shopDTO){
        ShopEntity shop =toEntity(shopDTO);
        Long userId=shopDTO.getOwnerId();
        UserEntity user = userRepository.findById(userId).orElse(null);
        shop.setOwner(user);
        shopEntityRepository.save(shop);
    }

    public static ShopEntity toEntity(ShopDTO shopDTO) {

        ShopEntity shopEntity = new ShopEntity();
        shopEntity.setId(shopDTO.getId()); // 새 쇼핑몰 생성 시 null일 수 있음
        shopEntity.setName(shopDTO.getName());
        shopEntity.setDescription(shopDTO.getDescription());
        shopEntity.setCategory(shopDTO.getCategory());




        // owner 정보는 여기서 설정하지 않음
        return shopEntity;
    }

    public ShopEntity update(ShopDTO shopDTO, Long shopId) {


        ShopEntity shopOrigin= shopEntityRepository.findById(shopId).orElse(null);
        shopOrigin.setDescription(shopDTO.getDescription());
        shopOrigin.setName(shopDTO.getName());
        shopOrigin.setStatus(shopDTO.getStatus());

        shopEntityRepository.save(shopOrigin);


        return shopOrigin;


    }

    public List<ShopEntity> check() {

        List<ShopEntity> shopList=shopEntityRepository.findByApplying();
        return  shopList;


    }

    public void approve(Long shopId) {
        ShopEntity shop= shopEntityRepository.findById(shopId).orElse(null);
        shop.setStatus("Accept");

    }

    public void reject(Long shopId) {
        ShopEntity shop= shopEntityRepository.findById(shopId).orElse(null);
        shop.setStatus("reject");

    }




}
