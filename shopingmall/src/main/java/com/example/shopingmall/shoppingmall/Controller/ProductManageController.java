package com.example.shopingmall.shoppingmall.Controller;

import com.example.shopingmall.shoppingmall.Dto.ProductDTO;
import com.example.shopingmall.shoppingmall.Service.ProductService;
import com.example.shopingmall.shoppingmall.Service.ShopService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductManageController {

    private final ProductService service;

    public ProductManageController(ProductService service) {
        this.service = service;
    }

    @PostMapping("/shops/{shopId}/products")
    public ResponseEntity<?> saveProduct(@PathVariable Long shopId, @RequestBody ProductDTO product, Authentication authentication) {
        try {
            service.AddProduct(shopId, authentication.name(), product);
            return ResponseEntity.ok().body("Product saved successfully.");
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred: " + e.getMessage());
        }
    }

    @PutMapping("/shops/{shopId}/products/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable Long shopId, @PathVariable Long productId, @RequestBody ProductDTO product, Authentication authentication) {

        try {
            service.updateProduct(shopId, productId, authentication.name(), product);
            return ResponseEntity.ok().body("Product updaretd successfully.");
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred: " + e.getMessage());
        }
    }

    @DeleteMapping("/shops/{shopId}/products/{productId}/delete")
    public ResponseEntity<?> deleteProduct(@PathVariable Long shopId, @PathVariable Long productId, @RequestBody ProductDTO product, Authentication authentication) {

        try {
            service.delete(shopId, productId, authentication.name());
            return ResponseEntity.ok().body("Product deleted successfully.");
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred: " + e.getMessage());
        }
    }
}
