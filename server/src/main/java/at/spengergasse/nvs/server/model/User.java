package at.spengergasse.nvs.server.model;

import at.spengergasse.nvs.server.dto.UserDto;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;
import java.util.UUID;


/**
 * Any Information belonging to User is stored here (Authentication mainly)
 */
@Entity
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    /**
     * Identifier for later purposes (Email-generator-favorites).
     */
    @Id
    private String identifier;

    /**
     * The Email identifies the User and can not be null.
     * The Email must be at least 10 characters long.
     */
    @NotNull
    @Size(min = 10)
    private String email;

    /**
     * The password identifies the User and can not be null.
     * The password must be at least 6 characters long.
     */
    @Size(min = 6)
    @NotNull
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
     * @param userDto userDto mapped to User
     */
    public User (UserDto userDto){
        this.identifier = Optional.ofNullable(userDto.getIdentifier()).orElse(UUID.randomUUID().toString());
        this.firstName = userDto.getFirstName();
        this.lastName = userDto.getLastName();
        this.email = userDto.getEmail().toLowerCase();
        this.password = userDto.getPassword();
        this.token = userDto.getToken();
    }

}
