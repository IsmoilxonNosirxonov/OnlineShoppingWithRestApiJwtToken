package uz.in.onlineshoppingwithrestapijwttoken.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.in.onlineshoppingwithrestapijwttoken.domain.dto.OrderCreateDto;
import uz.in.onlineshoppingwithrestapijwttoken.domain.entity.Order;
import uz.in.onlineshoppingwithrestapijwttoken.service.order.OrderService;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/customer/create-order")
    public ResponseEntity<Order> create(@RequestBody OrderCreateDto createDto) {
        return ResponseEntity.ok(orderService.save(createDto));
    }

    @DeleteMapping("/customer/delete-order/{id}")
    public ResponseEntity<Order> deleteById(@PathVariable UUID id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/customer/update-order/{id}")
    public ResponseEntity<Order> update(@RequestBody OrderCreateDto createDto, @PathVariable UUID id) {
        return ResponseEntity.ok(orderService.update(createDto, id));
    }

    @GetMapping("/customer/get-order/{id}")
    public ResponseEntity<Order> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(orderService.getById(id));
    }

    @GetMapping("/get-all-orders")
    public ResponseEntity<List<?>> getAll() {
        return ResponseEntity.ok(orderService.getAll());
    }
}
