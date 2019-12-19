package at.spengergasse.nvs.server.dto;

import at.spengergasse.nvs.server.model.Template;
import at.spengergasse.nvs.server.model.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

/**
 * Any Information belonging to Template is stored here
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TemplateDto extends ResourceSupport {

    /**
     * Identifier for later purposes (Email-generator-favorites).
     */
    private String identifier;

    /**
     * Information needed for the Template creation and convenience.
     */
    private String name;
    private String mailto;
    private String cc;
    private String subject;
    private String body;

    /**
     * Used for custom mapping processes
     * @param template Template mapped to TemplateDto
     */
    public TemplateDto (Template template){
        this.identifier = template.getIdentifier();
        this.name = template.getName();
        this.mailto = template.getMailto();
        this.cc = template.getCc();
        this.subject = template.getSubject();
        this.body = template.getBody();
    }
}
