package internet.shop.repository;

import internet.shop.model.entity.Camp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CampRepository extends JpaRepository<Camp, Long> {

    boolean existsByIdAndRemovedFalse(Long id);

    Camp getByIdAndRemovedFalse(Long id);

    List<Camp> getAllByRemovedFalse();

    Optional<Camp> findByNameAndRemovedFalse(String name);
}
