package at.spengergasse.nvs.server.persistence;

import at.spengergasse.nvs.server.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TemplateRepository extends JpaRepository<Template, Long> {

    Optional<Template> findByIdentifier(String id);

    List<Template> findByUserIdentifier(String identifier);

    void deleteByIdentifier(String id);

}
