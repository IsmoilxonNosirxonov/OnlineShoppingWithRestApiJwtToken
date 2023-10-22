package uz.in.onlineshoppingwithrestapijwttoken.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.in.onlineshoppingwithrestapijwttoken.domain.dto.OrderItemCreateDto;
import uz.in.onlineshoppingwithrestapijwttoken.domain.entity.OrderItem;
import uz.in.onlineshoppingwithrestapijwttoken.service.orderitem.OrderItemService;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @PostMapping("/customer/create-order-item")
    public ResponseEntity<OrderItem> create(@RequestBody OrderItemCreateDto createDto) {
        return ResponseEntity.ok(orderItemService.save(createDto));
    }

    @DeleteMapping("/customer/delete-order-item/{id}")
    public ResponseEntity<OrderItem> deleteById(@PathVariable UUID id) {
        orderItemService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/customer/update-order-item/{id}")
    public ResponseEntity<OrderItem> update(@RequestBody OrderItemCreateDto createDto, @PathVariable UUID id) {
        return ResponseEntity.ok(orderItemService.update(createDto, id));
    }

    @GetMapping("/customer/get-order-item/{id}")
    public ResponseEntity<OrderItem> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(orderItemService.getById(id));
    }

    @GetMapping("/get-all-order-items")
    public ResponseEntity<List<?>> getAll() {
        return ResponseEntity.ok(orderItemService.getAll());
    }
}
