package at.spengergasse.nvs.server.dto;

import at.spengergasse.nvs.server.model.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String token;

    public UserDto (User user){
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.token = user.getToken();
    }

}
