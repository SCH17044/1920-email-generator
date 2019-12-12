package at.spengergasse.nvs.server.dto;

import at.spengergasse.nvs.server.model.Template;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TemplateDto extends ResourceSupport {

    private String identifier;
    private String mailto;
    private String cc;
    private String subject;
    private String body;

    public TemplateDto (Template template){
        this.identifier = template.getIdentifier();
        this.mailto = template.getMailto();
        this.cc = template.getCc();
        this.subject = template.getSubject();
        this.body = template.getBody();
    }
}
