package internet.shop.repository;

import internet.shop.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PlaceRepository extends JpaRepository<Place, Long> {

    Place getByIdAndRemovedFalse(Long id);

    boolean existsByIdAndRemovedFalse(Long id);

}