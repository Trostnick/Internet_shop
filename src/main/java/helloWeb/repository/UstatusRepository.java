package helloWeb.repository;

import helloWeb.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UstatusRepository extends JpaRepository<UserStatus, Long> {


}