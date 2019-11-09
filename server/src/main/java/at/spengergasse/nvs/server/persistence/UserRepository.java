package at.spengergasse.nvs.server.persistence;

import at.spengergasse.nvs.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findById(String id);

    Optional<User> findUser(String email, String password);
}
