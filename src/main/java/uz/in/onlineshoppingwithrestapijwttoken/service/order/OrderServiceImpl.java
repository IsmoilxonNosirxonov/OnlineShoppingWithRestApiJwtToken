package uz.in.onlineshoppingwithrestapijwttoken.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.in.onlineshoppingwithrestapijwttoken.domain.Enum.OrderStatus;
import uz.in.onlineshoppingwithrestapijwttoken.domain.dto.OrderCreateDto;
import uz.in.onlineshoppingwithrestapijwttoken.domain.entity.Order;
import uz.in.onlineshoppingwithrestapijwttoken.domain.entity.OrderItem;
import uz.in.onlineshoppingwithrestapijwttoken.domain.entity.User;
import uz.in.onlineshoppingwithrestapijwttoken.exception.BalanceNotEnoughException;
import uz.in.onlineshoppingwithrestapijwttoken.exception.DataNotFoundException;
import uz.in.onlineshoppingwithrestapijwttoken.repository.OrderItemRepository;
import uz.in.onlineshoppingwithrestapijwttoken.repository.OrderRepository;
import uz.in.onlineshoppingwithrestapijwttoken.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    private final UserRepository userRepository;

    public Order save(OrderCreateDto createDto) {
        double totalPrice = 0;
        List<OrderItem> orderItems=new ArrayList<>();
        for (UUID  orderItemId: createDto.getOrderItemIds()) {
            OrderItem orderItem = orderItemRepository.findById(orderItemId).get();
            orderItems.add(orderItem);
            totalPrice+=orderItem.getProduct().getPrice()*orderItem.getAmount();
        }
        User user = userRepository.findById(createDto.getUserId()).get();
        if (user.getBalance()>=totalPrice) {
            Order order = Order.builder()
                    .orderItems(orderItems)
                    .totalPrice(totalPrice)
                    .user(user)
                    .status(OrderStatus.CREATED)
                    .build();
            user.setBalance(user.getBalance()-totalPrice);//Change the user balance
            userRepository.save(user);
            return orderRepository.save(order);
        }else
            throw new BalanceNotEnoughException("Balance is not enough!");
    }
    @Override
    public void delete(UUID id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Order update(OrderCreateDto createDto, UUID id) {
        double totalPrice = 0;
        List<OrderItem> orderItems=new ArrayList<>();
        for (UUID orderItemId : createDto.getOrderItemIds()) {
            OrderItem orderItem = orderItemRepository.findById(orderItemId).get();
            orderItems.add(orderItem);
            totalPrice+=orderItem.getProduct().getPrice()*orderItem.getAmount();
        }
        Optional<Order> byId = orderRepository.findById(id);
        User user = userRepository.findById(createDto.getUserId()).get();
        if(byId.isPresent() && user.getBalance()+byId.get().getTotalPrice()>=totalPrice){
            Order order = byId.get();
            order.setOrderItems(orderItems);
            order.setTotalPrice(totalPrice);
            order.setStatus(OrderStatus.UPDATED);
            orderRepository.save(order);
            user.setBalance(user.getBalance()+byId.get().getTotalPrice()-totalPrice);//Change the user balance
            userRepository.save(user);
            return byId.get();
        }
        else
            throw new BalanceNotEnoughException("Balance is not enough or Order not found!");
    }

    @Override
    public Order getById(UUID id) {
        return orderRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Order not found"));
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }
}
