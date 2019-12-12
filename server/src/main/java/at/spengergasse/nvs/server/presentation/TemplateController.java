package at.spengergasse.nvs.server.presentation;

import at.spengergasse.nvs.server.dto.TemplateDto;
import at.spengergasse.nvs.server.service.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/templates")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class TemplateController {

    private final TemplateService templateService;
    private final UserController userController;

    @GetMapping
    ResponseEntity<Resources<TemplateDto>> findAll() {
        return ResponseEntity.ok(
                new Resources<>(
                        templateService.findAllTemplates()
                                .stream()
                                .map(this::addSelfLink)
                                .collect(Collectors.toList())));
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<TemplateDto> findById(@PathVariable String id) {
        return ResponseEntity.of(
                templateService.findByTemplateId(id)
                        .map(this::addSelfLink));
    }

    @GetMapping(path = "/getUsersByIdentifier/{identifier}")
    ResponseEntity<List<TemplateDto>> findAllByUser(@PathVariable String identifier){
        return ResponseEntity.ok(
                templateService.findAllByUserIdentifier(identifier)
                        .stream()
                        .map(this::addSelfLink)
                        .collect(Collectors.toList()));
    }

    @PostMapping(path = "/{userIdentifier}")
    ResponseEntity<TemplateDto> create(@RequestBody TemplateDto dto, @PathVariable String userIdentifier) {
        return ResponseEntity.ok(
                templateService.saveTemplate(dto, userIdentifier)
                        .map(this::addSelfLink)
                        .orElseThrow(IllegalAccessError::new)
        );
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {

        templateService.deleteByTemplateId(id);

        return ResponseEntity.ok()
                .build();
    }

    TemplateDto addSelfLink(TemplateDto dto){
        Link selfLink = linkTo(
                methodOn(this.getClass())
                        .findById(dto.getIdentifier())
        ).withSelfRel();

        dto.add(selfLink);
        return dto;
    }

}
