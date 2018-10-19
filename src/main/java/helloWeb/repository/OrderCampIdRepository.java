package helloWeb.repository;

import helloWeb.entity.OrderCampId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface OrderCampIdRepository extends JpaRepository<OrderCampId, Long> {


}