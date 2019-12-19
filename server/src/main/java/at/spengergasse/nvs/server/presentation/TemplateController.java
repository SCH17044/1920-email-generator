package at.spengergasse.nvs.server.presentation;

import at.spengergasse.nvs.server.dto.TemplateDto;
import at.spengergasse.nvs.server.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for Template
 */
@RestController
@RequestMapping(path = "/templates")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TemplateController {
    /**
     * Template Service instance
     */
    private final TemplateService templateService;

    /**
     * Finds a Template by its id.
     * @param id
     * @return the found template as a response entity
     */
    @GetMapping(path = "/{id}")
    ResponseEntity<TemplateDto> findById(@PathVariable String id) {
        return ResponseEntity.of(
                templateService.findByTemplateId(id));
    }

    /**
     * Finds Templates by a user Identifier
     * @param identifier of a User
     * @return a List of Templates
     */
    @GetMapping(path = "/getUsersByIdentifier/{identifier}")
    ResponseEntity<List<TemplateDto>> findAllByUser(@PathVariable String identifier){
        return ResponseEntity.ok(
                templateService.findAllByUserIdentifier(identifier));
    }

    /**
     * Creates a Template with the given userIdentifier
     * @param dto template to create
     * @param userIdentifier user Identifier
     * @return the created template or null
     */
    @PostMapping(path = "/{userIdentifier}")
    ResponseEntity<TemplateDto> create(@RequestBody TemplateDto dto, @PathVariable String userIdentifier) {
        return ResponseEntity.ok(
                templateService.saveTemplate(dto, userIdentifier)
                        .orElseThrow(IllegalAccessError::new)
        );
    }

    /**
     * Deletes the Template with the given id
     * @param id of the template
     * @return a Response entity
     */
    @DeleteMapping(path = "/{id}")
    public void deleteById(@PathVariable String id) {
        templateService.deleteByTemplateId(id);
    }

}
