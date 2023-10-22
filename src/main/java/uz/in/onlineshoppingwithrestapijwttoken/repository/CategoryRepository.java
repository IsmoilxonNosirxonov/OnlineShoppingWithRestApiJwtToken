package uz.in.onlineshoppingwithrestapijwttoken.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import uz.in.onlineshoppingwithrestapijwttoken.domain.entity.Category;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID>, JpaSpecificationExecutor<Category> {

    Optional<Category> findByName(String name);
}
