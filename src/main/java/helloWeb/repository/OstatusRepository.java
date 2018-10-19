package helloWeb.repository;

import helloWeb.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface OstatusRepository extends JpaRepository<OrderStatus, Long> {


}