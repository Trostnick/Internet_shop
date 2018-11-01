package internet.shop.repository;

import internet.shop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User getByLoginAndRemovedFalse(String login);

    boolean existsByIdAndRemovedFalse(Long id);

    User getByIdAndRemovedFalse(Long id);

}