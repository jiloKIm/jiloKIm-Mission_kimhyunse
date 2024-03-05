package com.example.shopingmall.jungomarket.Service;

import com.example.shopingmall.jungomarket.Dto.ProductDto;
import com.example.shopingmall.jungomarket.Entity.ProductEntity;
import com.example.shopingmall.jungomarket.repo.ProductRepository;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void saveItem(ProductDto product){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setTitle(product.getTitle());
        productEntity.setDescription(product.getDescription());
        productEntity.setMinPrice(product.getMinPrice());
        productEntity.setStatus(product.getStatus());
        productRepository.save(productEntity);
    }

    public List<ProductDto> findAllitem() {
        List<ProductEntity> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for (ProductEntity product : products) {
            ProductDto dto = new ProductDto();
            dto.setId(product.getId());
            dto.setTitle(product.getTitle());
            dto.setDescription(product.getDescription());
            dto.setMinPrice(product.getMinPrice());
            dto.setStatus(product.getStatus());
            productDtos.add(dto);
        }
    return  productDtos;
    }

    public ProductDto findItem(Long id) {

        ProductEntity product = productRepository.findById(id)
                .orElse(null);

        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setDescription(product.getDescription());
        dto.setMinPrice(product.getMinPrice());
        dto.setStatus(product.getStatus());

        return dto;
    }

    public void updateItem(Long id,ProductDto uproduct,String currentUsername) throws AccessDeniedException {
        ProductEntity product = productRepository.findById(id)
                .orElse(null);
        if (product.getOwner() == null || !product.getOwner().getUsername().equals(currentUsername)) {
            throw new AccessDeniedException("수정권한 x.");
        }
        ProductEntity productEntity = new ProductEntity();
        productEntity.setTitle(uproduct.getTitle());
        productEntity.setDescription(uproduct.getDescription());
        productEntity.setMinPrice(uproduct.getMinPrice());
        productEntity.setStatus(uproduct.getStatus());

        productRepository.save(productEntity);

    }

    public void deleteItem(Long productId, Authentication authentication) throws AccessDeniedException {

        ProductEntity product= productRepository.findById(productId).orElse(null);

        if(product.getOwner() ==null || !product.getOwner().getUsername().equals(authentication.name())){

            throw new AccessDeniedException("수정권한 x");

        }

        productRepository.deleteById(productId);

    }
}
