package internet.shop.repository;

import internet.shop.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserStatusRepository extends JpaRepository<UserStatus, Long> {


}