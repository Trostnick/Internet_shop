package helloWeb.repository;

import helloWeb.entity.CampType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CtypeRepository extends JpaRepository<CampType, Long> {


}