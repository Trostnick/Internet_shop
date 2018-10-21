package internet.shop.repository;

import internet.shop.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PlaceRepository extends JpaRepository<Place, Long> {


}