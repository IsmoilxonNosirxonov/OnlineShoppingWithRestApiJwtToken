package uz.in.onlineshoppingwithrestapijwttoken.service.orderitem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.in.onlineshoppingwithrestapijwttoken.domain.dto.OrderItemCreateDto;
import uz.in.onlineshoppingwithrestapijwttoken.domain.entity.OrderItem;
import uz.in.onlineshoppingwithrestapijwttoken.domain.entity.Product;
import uz.in.onlineshoppingwithrestapijwttoken.exception.DataNotFoundException;
import uz.in.onlineshoppingwithrestapijwttoken.repository.OrderItemRepository;
import uz.in.onlineshoppingwithrestapijwttoken.repository.ProductRepository;
import uz.in.onlineshoppingwithrestapijwttoken.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService{

    private final OrderItemRepository orderItemRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    public OrderItem save(OrderItemCreateDto createDto) {
        Product product = productRepository.findById(createDto.getProductId()).get();
        OrderItem basket= OrderItem.builder()
                .product(product)
                .amount(createDto.getAmount())
                .user(userRepository.findById(createDto.getUserId()).get())
                .build();
        product.setQuantity(product.getQuantity()-createDto.getAmount());//Change the product quantity
        productRepository.save(product);
        return orderItemRepository.save(basket);
    }

    @Override
    public void delete(UUID id) {
        orderItemRepository.deleteById(id);
    }

    @Override
    public OrderItem update(OrderItemCreateDto createDto, UUID id) {
        Optional<OrderItem> byId = orderItemRepository.findById(id);
        Product product = productRepository.findById(createDto.getProductId()).get();
        if(byId.isPresent()){
            OrderItem orderItem = byId.get();
            orderItem.setProduct(product);
            orderItem.setAmount(createDto.getAmount());
            orderItemRepository.save(orderItem);
            product.setQuantity(product.getQuantity()+byId.get().getAmount()-createDto.getAmount());//Change the product quantity
            productRepository.save(product);
            return byId.get();
        }
        OrderItem basket= OrderItem.builder()
                .product(productRepository.findById(createDto.getProductId()).get())
                .amount(createDto.getAmount())
                .user(userRepository.findById(createDto.getUserId()).get())
                .build();
        return basket;
    }

    @Override
    public OrderItem getById(UUID id) {
        return orderItemRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Basket not found"));
    }

    @Override
    public List<OrderItem> getAll() {
        return orderItemRepository.findAll();
    }
}
