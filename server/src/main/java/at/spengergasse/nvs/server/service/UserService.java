package at.spengergasse.nvs.server.service;

import at.spengergasse.nvs.server.dto.UserDto;
import at.spengergasse.nvs.server.model.User;
import at.spengergasse.nvs.server.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserService {

    private final UserRepository userRepository;

    public Optional<UserDto> createUser(UserDto userDto){
        User user = Optional.of(userDto).map(User::new).get();
        return Optional.of(userRepository.save(user)).map(UserDto::new);
    }

    public Optional<UserDto> findUserById(String id){
        return userRepository.findById(id).map(UserDto::new);
    }

    public boolean authenticateUser(UserDto userDto){
        User user =  Optional.of(userDto).map(User::new).get();
        return userRepository.findUser(user.getEmail(), user.getPassword()).isPresent();
    }

}
