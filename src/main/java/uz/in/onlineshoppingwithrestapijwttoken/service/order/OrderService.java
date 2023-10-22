package uz.in.onlineshoppingwithrestapijwttoken.service.order;

import org.springframework.stereotype.Service;
import uz.in.onlineshoppingwithrestapijwttoken.domain.dto.OrderCreateDto;
import uz.in.onlineshoppingwithrestapijwttoken.domain.entity.Order;
import uz.in.onlineshoppingwithrestapijwttoken.service.BaseService;

@Service
public interface OrderService extends BaseService<OrderCreateDto, Order> {
}
