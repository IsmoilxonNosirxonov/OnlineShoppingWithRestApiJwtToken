package uz.in.onlineshoppingwithrestapijwttoken.service.category;

import org.springframework.stereotype.Service;
import uz.in.onlineshoppingwithrestapijwttoken.domain.dto.CategoryCreateDto;
import uz.in.onlineshoppingwithrestapijwttoken.domain.entity.Category;
import uz.in.onlineshoppingwithrestapijwttoken.service.BaseService;


@Service
public interface CategoryService extends BaseService<CategoryCreateDto, Category> {
}
