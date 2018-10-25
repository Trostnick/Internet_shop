package internet.shop.repository;

import internet.shop.entity.OrderCamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface OrderCampRepository extends JpaRepository<OrderCamp, Long> {


}