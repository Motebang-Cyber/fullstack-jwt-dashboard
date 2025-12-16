package in.letsengmg.jwtDashboardBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import in.letsengmg.jwtDashboardBackend.entity.User;
import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByResetToken(String token);
}
