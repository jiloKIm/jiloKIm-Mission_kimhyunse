package com.example.shopingmall.shoppingmall.Service;

import com.example.shopingmall.jungomarket.Entity.ProductEntity;
import com.example.shopingmall.shoppingmall.Dto.ProductDTO;
import com.example.shopingmall.shoppingmall.Entity.ShopEntity;
import com.example.shopingmall.shoppingmall.Entity.ShopProductEntity;
import com.example.shopingmall.shoppingmall.Repository.ProductEntityRepository;
import com.example.shopingmall.shoppingmall.Repository.ShopEntityRepository;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@Service
public class ProductService {
    private final ShopEntityRepository shopEntityRepository;
    private final ProductEntityRepository productEntityRepository;
    public ProductService(ShopEntityRepository shopEntityRepository, ProductEntityRepository productEntityRepository) {
        this.shopEntityRepository = shopEntityRepository;
        this.productEntityRepository = productEntityRepository;
    }

    public void AddProduct(Long shopId, String username, ProductDTO productDTO)  {
        ShopEntity shop = shopEntityRepository.findById(shopId).orElse(null);
        if (shop != null && shop.getOwner().getUsername().equals(username)) {
            ShopProductEntity productEntity = new ShopProductEntity();
            productEntity.setTitle(productDTO.getTitle());
            productEntity.setDescription(productDTO.getDescription());
            productEntity.setMinPrice(productDTO.getMinPrice());
            productEntity.setStock(productDTO.getStock());
            productEntity.setStatus("AVAILABLE");
            productEntity.setShop(shop);
            productEntity.setOwner(shop.getOwner());

            productEntityRepository.save(productEntity);

        } else {
            // 쇼핑몰이 존재하지 않거나, 사용자가 쇼핑몰의 소유자가 아닌 경우 처리
            throw new IllegalStateException("Shop does not exist or user is not the owner.");
        }
    }


    public void updateProduct(Long productId,Long shopId, String name, ProductDTO productDTO) {
        ShopProductEntity productEntity= productEntityRepository.findById(productId).orElse(null);
        ShopEntity shop = shopEntityRepository.findById(shopId).orElse(null);
        if (shop != null && shop.getOwner().getUsername().equals(name)) {
            productEntity.setTitle(productDTO.getTitle());
            productEntity.setDescription(productDTO.getDescription());
            productEntity.setMinPrice(productDTO.getMinPrice());
            productEntity.setStock(productDTO.getStock());
            productEntity.setStatus(productEntity.getStatus());
            productEntity.setShop(shop);
            productEntity.setOwner(shop.getOwner());

        }else {
            // 쇼핑몰이 존재하지 않거나, 사용자가 쇼핑몰의 소유자가 아닌 경우 처리
            throw new IllegalStateException("Shop does not exist or user is not the owner.");
        }






    }

    public void delete(Long shopId, Long productId, String name) {


        ShopEntity shop = shopEntityRepository.findById(shopId).orElse(null);

        if (shop != null && shop.getOwner().getUsername().equals(name)) {

            productEntityRepository.deleteById(productId);

        }else {
            throw new IllegalStateException("Shop does not exist or user is not the owner.");
        }



    }
}
