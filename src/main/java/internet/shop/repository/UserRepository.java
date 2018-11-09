package internet.shop.repository;

import internet.shop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User getByLoginAndRemovedFalse(String login);

    boolean existsByIdAndRemovedFalse(Long id);

    User getByIdAndRemovedFalse(Long id);

    Optional<User> findByLoginAndRemovedFalse(String login);

}