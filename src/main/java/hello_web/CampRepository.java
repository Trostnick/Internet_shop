package hello_web;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CampRepository extends CrudRepository<Camp, Long> {

    List<Camp> findByName(String name);

    List<Camp> findAllByName(String name);

}
