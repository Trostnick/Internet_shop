package internet.shop.repository;

import internet.shop.entity.CampType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampTypeRepository extends JpaRepository<CampType, Long> {

    List<CampType> getAllByNameNotNull();

}