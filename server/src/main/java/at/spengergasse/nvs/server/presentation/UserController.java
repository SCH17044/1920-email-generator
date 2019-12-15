package at.spengergasse.nvs.server.presentation;

import at.spengergasse.nvs.server.dto.UserDto;
import at.spengergasse.nvs.server.model.User;
import at.spengergasse.nvs.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller for User
 */
@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserController {

    /**
     * User Service instance
     */
    private final UserService userService;

    /**
     * registers the user
     * path is set to /users/register
     * @param userDto registration Input mapped to User
     * @return returns the response if the creation was successful
     */
    @PostMapping(path="/register")
    public ResponseEntity<Optional<UserDto>> registerUser(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    /**
     * Only for Testing purposes (PostMan)
     * path is not set so it is /users
     * @return all Users
     */
    @GetMapping
    ResponseEntity<List<UserDto>> findAll(){
        return ResponseEntity.ok(userService.findAllUsers());
    }

    /**
     * Authenticates the User
     * @param userDto login Input mapped to User
     * @return returns the response if the login was successful or not (Unauthorized)
     */
    @PostMapping("/login")
    public ResponseEntity<User> authenticateUser(@RequestBody UserDto userDto){
        User user = userService.loginUser(userDto);
        HttpStatus httpStatus =  user != null ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity
                .status(httpStatus)
                .body(user);
    }

}
