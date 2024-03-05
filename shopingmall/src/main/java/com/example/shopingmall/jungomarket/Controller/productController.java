package com.example.shopingmall.jungomarket.Controller;

import com.example.shopingmall.jungomarket.Dto.ProductDto;
import com.example.shopingmall.jungomarket.Service.ProductService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/jungo")
public class productController {

    private final ProductService productService;

    public productController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public ResponseEntity<String> enroll(@RequestBody ProductDto productDto) {
        // ProductDto 객체의 필수 필드 검사

        if (productDto.getTitle() == null || productDto.getDescription() == null || productDto.getMinPrice() == null) {
            return new ResponseEntity<>("다시 기입하세요", HttpStatus.BAD_REQUEST);
        }

        productService.saveItem(productDto);

        return ResponseEntity.ok("물품 등록 완료");
    }


    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> products() {

        List<ProductDto> items = productService.findAllitem();
        // 상태 코드 200 OK와 함께, 본문에 items를 JSON 형태로 반환
        return ResponseEntity.ok().body(items);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductDto> productsdetail(@PathVariable Long productId) {

        ProductDto dto = productService.findItem(productId);
        // 상태 코드 200 OK와 함께, 본문에 items를 JSON 형태로 반환
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping ("/products/{productId}")
    public ResponseEntity<?>  updateproducts(@PathVariable Long productId, @RequestBody ProductDto productDto, Authentication authentication) throws AccessDeniedException {


        productService.updateItem(productId,productDto,authentication.name());

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/products/{productId}")
    public void productsdelite(@PathVariable Long productId, Authentication authentication) throws AccessDeniedException {


        productService.deleteItem(productId,authentication);

    }


}
