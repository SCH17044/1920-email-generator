package at.spengergasse.nvs.server.service;

import at.spengergasse.nvs.server.dto.UserDto;
import at.spengergasse.nvs.server.model.User;
import at.spengergasse.nvs.server.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service for the User
 */
@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserService {

    /**
     * User Repository Instance
     */
    private final UserRepository userRepository;

    /**
     * Password Encoder Instance (for password encoding)
     */
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * Creation of a User
     * Throws an Error if the User with the exact Email does already exist
     * Encodes the password, if the User does not exist yet and stores it
     * @param userDto registration Input of the User
     * @return the created User
     */
    public Optional<UserDto> createUser(UserDto userDto){
        if(userRepository.findByEmail(userDto.getEmail()) != null){
            throw new UsernameNotFoundException("Username is already taken!");
        }
        User user = Optional.of(userDto).map(User::new).get();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return Optional.of(userRepository.save(user)).map(UserDto::new);
    }

    /**
     * Authentication of the User
     * Throws an Error if the User with the given Email does not exist
     * Checks if the Email and password is correct (Match method for encoded password comparison)
     * @param userDto login Input of the User
     * @return true when authentication was successful otherwise return false
     */
    public User loginUser(UserDto userDto){
        User loginUser =  Optional.of(userDto).map(User::new).get();
        User checkUser = userRepository.findByEmail(loginUser.getEmail());
        if(checkUser == null){
            throw new UsernameNotFoundException("Invalid Username or password!");
        }
        if(passwordEncoder.matches(loginUser.getPassword(), checkUser.getPassword())){
            return checkUser;
        };
        return null;
    }

    /**
     * For Testing Purposes
     * Returns all the Users
     * @return all Users
     */
    public List<UserDto> findAllUsers(){
        return userRepository
                .findAll()
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

}
