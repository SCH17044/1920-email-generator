package at.spengergasse.nvs.server.model;

import at.spengergasse.nvs.server.dto.TemplateDto;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Optional;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Template extends AbstractPersistable<Long> {

    private String identifier;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;
    private String mailto;
    private String cc;
    private String subject;
    private String body;

    public Template(TemplateDto templateDto) {
        this.identifier = Optional.ofNullable(templateDto.getIdentifier()).orElse(UUID.randomUUID().toString());
        this.mailto = templateDto.getMailto();
        this.cc = templateDto.getCc();
        this.subject = templateDto.getSubject();
        this.body = templateDto.getBody();
    }
}
