package internet.shop.repository;

import internet.shop.entity.CampType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CampTypeRepository extends JpaRepository<CampType, Long> {


}