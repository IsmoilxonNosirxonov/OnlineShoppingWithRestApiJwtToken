package uz.in.onlineshoppingwithrestapijwttoken.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.in.onlineshoppingwithrestapijwttoken.domain.dto.ProductCreateDto;
import uz.in.onlineshoppingwithrestapijwttoken.domain.entity.Product;
import uz.in.onlineshoppingwithrestapijwttoken.service.product.ProductService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/seller/create-product")
    public ResponseEntity<Product> create(@RequestBody ProductCreateDto createDto) {
        return ResponseEntity.ok(productService.save(createDto));
    }

    @DeleteMapping("/seller/delete-product/{id}")
    public ResponseEntity<Product> deleteById(@PathVariable UUID id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/seller/update-product/{id}")
    public ResponseEntity<Product> update(@RequestBody ProductCreateDto createDto, @PathVariable UUID id) {
        return ResponseEntity.ok(productService.update(createDto, id));
    }

    @GetMapping("/get-product/{id}")
    public ResponseEntity<Product> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @GetMapping("/get-all-products")
    public ResponseEntity<List<?>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }
}
