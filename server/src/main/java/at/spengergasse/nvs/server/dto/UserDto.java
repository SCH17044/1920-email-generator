package at.spengergasse.nvs.server.dto;

import at.spengergasse.nvs.server.model.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

/**
 * Any Information belonging to User is stored here (Authentication mainly)
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto extends ResourceSupport {

    /**
     * Identifier for later purposes (Email-generator-favorites).
     */
    private String identifier;

    /**
     * The Email identifies the User and can not be null.
     */
    private String email;

    /**
     * The Password identifies the User and can not be null.
     */
    private String password;

    /**
     * Session token will be used later.
     */
    private String token;

    /**
     * User Data for later Email-Generation.
     */
    private String firstName;
    private String lastName;

    /**
     * Used for custom mapping processes
     * @param user User mapped to UserDto
     */
    public UserDto (User user){
        this.identifier = user.getIdentifier();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.token = user.getToken();
    }

}
