package internet.shop.repository;

import internet.shop.entity.Camp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampRepository extends JpaRepository<Camp, Long> {

    boolean existsByIdAndRemovedFalse(Long id);

    Camp getByIdAndRemovedFalse(Long id);

    List<Camp> getAllByRemovedFalse();

}
