package at.spengergasse.nvs.server.model;

import at.spengergasse.nvs.server.dto.TemplateDto;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Any Information belonging to Template is stored here
 */
@Entity
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Template {

    /**
     * Identifier of the object.
     */
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "template_id")
    private String identifier;

    /**
     * User Reference (of the creating user).
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;

    /**
     * Information needed for the Template creation and convenience.
     */
    private String name;
    private String mailto;
    private String cc;
    private String bcc;
    private String subject;
    private String body;

    /**
     * Used for custom mapping processes
     * @param templateDto templateDto mapped to Template
     */
    public Template(TemplateDto templateDto) {
        this.identifier = templateDto.getIdentifier();
        this.name = templateDto.getName();
        this.mailto = templateDto.getMailto();
        this.cc = templateDto.getCc();
        this.bcc = templateDto.getBcc();
        this.subject = templateDto.getSubject();
        this.body = templateDto.getBody();
    }
}
