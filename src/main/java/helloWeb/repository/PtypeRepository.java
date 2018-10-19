package helloWeb.repository;

import helloWeb.entity.PlaceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PtypeRepository extends JpaRepository<PlaceType, Long> {


}