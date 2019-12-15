package at.spengergasse.nvs.server.service;

import at.spengergasse.nvs.server.dto.TemplateDto;
import at.spengergasse.nvs.server.model.Template;
import at.spengergasse.nvs.server.model.User;
import at.spengergasse.nvs.server.persistence.TemplateRepository;
import at.spengergasse.nvs.server.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service for the Template
 */
@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TemplateService {

    /**
     * Template Repository Instance
     */
    private final TemplateRepository templateRepository;
    /**
     * User Repository Instance
     */
    private final UserRepository userRepository;

    /**
     * Finds all Templates by User Identifier
     * @param identifier the user Identifier
     * @return a List of Templates
     */
    public List<TemplateDto> findAllByUserIdentifier(String identifier) {
        return templateRepository.findByUserIdentifier(identifier)
                        .stream()
                        .map(TemplateDto::new)
                        .collect(Collectors.toList());
    }

    /**
     * Saves the Template with the given Template and User Identifier
     * @param templateDto is the template information
     * @param identifier user Identifier for relation
     * @return the created object or null as an optional
     */
    public Optional<TemplateDto> saveTemplate(TemplateDto templateDto, String identifier) {
        User user = userRepository.findByIdentifier(identifier);
        Template template = Optional.of(templateDto).map(Template::new).get();
        template.setUser(user);
        return Optional.of(templateRepository.save(template)).map(TemplateDto::new);
    }

    /**
     * Finds the Template by the certain id
     * @param id the Template id
     * @return the Template or null as an Optional
     */
    public Optional<TemplateDto> findByTemplateId(String id) {
        return templateRepository.findByIdentifier(id).map(TemplateDto::new);
    }

    /**
     * Deletes the Template with the certain id
     * @param id of the Template
     */
    public void deleteByTemplateId(String id) {
        templateRepository.deleteByIdentifier(id);
    }

}
