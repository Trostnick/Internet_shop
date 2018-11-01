package internet.shop.repository;

import internet.shop.entity.OrderCamp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderCampRepository extends JpaRepository<OrderCamp, Long> {

    OrderCamp getByIdAndRemovedFalse(Long id);

    boolean existsByIdAndRemovedFalse(Long id);
}