package internet.shop.repository;

import internet.shop.model.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {

    OrderStatus getByName(String name);
}