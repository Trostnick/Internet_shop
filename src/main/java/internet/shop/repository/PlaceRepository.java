package internet.shop.repository;

import internet.shop.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PlaceRepository extends JpaRepository<Place, Long> {

    Place getByIdAndRemovedFalse(Long id);

    boolean existsByIdAndRemovedFalse(Long id);

    Optional<Place> findByNameAndRemovedFalse(String name);

}