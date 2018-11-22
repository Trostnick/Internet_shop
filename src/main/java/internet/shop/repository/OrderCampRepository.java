package internet.shop.repository;

import internet.shop.model.entity.OrderCamp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderCampRepository extends JpaRepository<OrderCamp, Long> {

    OrderCamp getByIdAndRemovedFalse(Long id);

    boolean existsByIdAndRemovedFalse(Long id);

    List<OrderCamp> findAllByOrderIdAndRemovedFalse(Long id);
}