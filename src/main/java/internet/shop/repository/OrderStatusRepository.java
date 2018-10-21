package internet.shop.repository;

import internet.shop.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {


}