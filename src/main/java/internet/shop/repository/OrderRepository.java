package internet.shop.repository;

import internet.shop.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUserIdAndRemovedFalse(Long id);

    Optional<Order> findByStatusNameAndUserLoginAndRemovedFalse(String name,String login);
}