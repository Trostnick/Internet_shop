package internet.shop.repository;

import internet.shop.model.entity.OrderPart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderCampRepository extends JpaRepository<OrderPart, Long> {

    OrderPart getByIdAndRemovedFalse(Long id);

    boolean existsByIdAndRemovedFalse(Long id);

    List<OrderPart> findAllByOrderIdAndRemovedFalse(Long id);
}