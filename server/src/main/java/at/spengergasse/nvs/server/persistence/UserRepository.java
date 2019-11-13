package at.spengergasse.nvs.server.persistence;

import at.spengergasse.nvs.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Provides all Methods for CRUD operations for the User
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds User by Email
     * @param email the email to search after
     * @return the found object or null
     */
    User findByEmail(String email);

}
