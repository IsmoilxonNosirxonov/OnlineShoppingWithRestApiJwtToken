package uz.in.onlineshoppingwithrestapijwttoken.service.orderitem;

import org.springframework.stereotype.Service;
import uz.in.onlineshoppingwithrestapijwttoken.domain.dto.OrderItemCreateDto;
import uz.in.onlineshoppingwithrestapijwttoken.domain.entity.OrderItem;
import uz.in.onlineshoppingwithrestapijwttoken.service.BaseService;

@Service
public interface OrderItemService extends BaseService<OrderItemCreateDto, OrderItem> {
}
