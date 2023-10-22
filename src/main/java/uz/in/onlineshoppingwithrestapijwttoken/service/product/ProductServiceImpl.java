package uz.in.onlineshoppingwithrestapijwttoken.service.product;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.in.onlineshoppingwithrestapijwttoken.domain.dto.ProductCreateDto;
import uz.in.onlineshoppingwithrestapijwttoken.domain.entity.Product;
import uz.in.onlineshoppingwithrestapijwttoken.exception.DataNotFoundException;
import uz.in.onlineshoppingwithrestapijwttoken.exception.DublicateValueException;
import uz.in.onlineshoppingwithrestapijwttoken.repository.CategoryRepository;
import uz.in.onlineshoppingwithrestapijwttoken.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    @Override
    public Product save(ProductCreateDto createDto) {
        if(productRepository.findByName(createDto.getName()).isPresent()){
            throw new DublicateValueException("This product already exists: "+createDto.getName());
        }
        Product product= Product.builder()
                .category(categoryRepository.findById(createDto.getCategoryId()).get())
                .name(createDto.getName())
                .description(createDto.getDescription())
                .quantity(createDto.getQuantity())
                .price(createDto.getPrice())
                .build();
        return productRepository.save(product);
    }

    @Override
    public void delete(UUID id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product update(ProductCreateDto createDto, UUID id) {
        Optional<Product> byId = productRepository.findById(id);
        if(byId.isPresent()){
            Product product = byId.get();
            product.setCategory(categoryRepository.findById(createDto.getCategoryId()).get());
            product.setName(createDto.getName());
            product.setDescription(createDto.getDescription());
            product.setQuantity(createDto.getQuantity());
            product.setPrice(createDto.getPrice());
            productRepository.save(product);
            return byId.get();
        }
        return modelMapper.map(createDto, Product.class);
    }

    @Override
    public Product getById(UUID id) {
        return productRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Product not found"));
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
