package at.spengergasse.nvs.server.persistence;

import at.spengergasse.nvs.server.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Provides all Methods for CRUD operations for the Template
 */
public interface TemplateRepository extends JpaRepository<Template, Long> {

    /**
     * Finds one Template by Id
     * @param id the id to search after
     * @return the found as an Optional or null
     */
    Optional<Template> findByIdentifier(String id);

    /**
     * Finds Template by Identifier
     * @param identifier the identifier to search after
     * @return a List of Templates or null
     */
    List<Template> findByUserIdentifier(String identifier);

}
