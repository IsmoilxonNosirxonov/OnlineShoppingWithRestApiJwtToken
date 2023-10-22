package uz.in.onlineshoppingwithrestapijwttoken.service.category;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.in.onlineshoppingwithrestapijwttoken.domain.dto.CategoryCreateDto;
import uz.in.onlineshoppingwithrestapijwttoken.domain.entity.Category;
import uz.in.onlineshoppingwithrestapijwttoken.exception.DataNotFoundException;
import uz.in.onlineshoppingwithrestapijwttoken.exception.DublicateValueException;
import uz.in.onlineshoppingwithrestapijwttoken.repository.CategoryRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private final ModelMapper modelMapper;

    @Override
    public Category save(CategoryCreateDto createDto) {
        if(categoryRepository.findByName(createDto.getName()).isPresent()){
            throw new DublicateValueException("This category already exists: "+createDto.getName());
        }
        return categoryRepository.save(modelMapper.map(createDto, Category.class));
    }

    @Override
    public void delete(UUID id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category update(CategoryCreateDto createDto, UUID id) {
        Optional<Category> byId = categoryRepository.findById(id);
        if(byId.isPresent()){
            Category category = byId.get();
            category.setName(createDto.getName());
            categoryRepository.save(category);
            return byId.get();
        }
        return modelMapper.map(createDto,Category.class);
    }

    @Override
    public Category getById(UUID id) {
        return categoryRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Category not found"));
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

}
