package internet.shop.repository;

import internet.shop.model.entity.CampPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampPhotoRepository extends JpaRepository<CampPhoto, Long> {

    List<CampPhoto> findAllByCamp_IdAndRemovedFalse(Long id);

    CampPhoto getByIdAndRemovedFalse(Long id);

}
