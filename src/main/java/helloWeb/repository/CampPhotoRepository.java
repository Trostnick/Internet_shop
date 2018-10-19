package helloWeb.repository;

import helloWeb.entity.CampPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CampPhotoRepository extends JpaRepository<CampPhoto, Long> {


}
