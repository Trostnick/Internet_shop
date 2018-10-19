package helloWeb.repository;

import java.util.List;

import helloWeb.entity.Camp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CampRepository extends JpaRepository<Camp, Long> {

    List<Camp> findByName(String name);

    List<Camp> findAllByName(String name);

}
