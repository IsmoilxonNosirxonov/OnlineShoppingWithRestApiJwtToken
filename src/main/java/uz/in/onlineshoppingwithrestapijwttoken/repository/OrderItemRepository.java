package uz.in.onlineshoppingwithrestapijwttoken.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import uz.in.onlineshoppingwithrestapijwttoken.domain.entity.OrderItem;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, UUID>, JpaSpecificationExecutor<OrderItem> {

    List<OrderItem> findByUserId(UUID userId);
}
