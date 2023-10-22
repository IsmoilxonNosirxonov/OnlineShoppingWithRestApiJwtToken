package uz.in.onlineshoppingwithrestapijwttoken.service.product;

import org.springframework.stereotype.Service;
import uz.in.onlineshoppingwithrestapijwttoken.domain.dto.ProductCreateDto;
import uz.in.onlineshoppingwithrestapijwttoken.domain.entity.Product;
import uz.in.onlineshoppingwithrestapijwttoken.service.BaseService;

@Service
public interface ProductService extends BaseService<ProductCreateDto, Product> {
}
