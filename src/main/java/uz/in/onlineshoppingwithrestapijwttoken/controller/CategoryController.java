package uz.in.onlineshoppingwithrestapijwttoken.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.in.onlineshoppingwithrestapijwttoken.domain.dto.CategoryCreateDto;
import uz.in.onlineshoppingwithrestapijwttoken.domain.entity.Category;
import uz.in.onlineshoppingwithrestapijwttoken.service.category.CategoryService;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/seller/create-category")
    public ResponseEntity<Category> create(@RequestBody CategoryCreateDto createDto) {
        return ResponseEntity.ok(categoryService.save(createDto));
    }

    @DeleteMapping("/seller/delete-category/{id}")
    public ResponseEntity<Category> deleteById(@PathVariable UUID id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/seller/update-category/{id}")
    public ResponseEntity<Category> update(@RequestBody CategoryCreateDto createDto, @PathVariable UUID id) {
        return ResponseEntity.ok(categoryService.update(createDto, id));
    }

    @GetMapping("/get-category/{id}")
    public ResponseEntity<Category> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @GetMapping("/get-all-categories")
    public ResponseEntity<List<?>> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }
}
