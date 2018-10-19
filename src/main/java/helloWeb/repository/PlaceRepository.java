package helloWeb.repository;

import helloWeb.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PlaceRepository extends JpaRepository<Place, Long> {


}