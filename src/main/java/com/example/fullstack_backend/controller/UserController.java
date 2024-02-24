package com.example.fullstack_backend.controller;
import com.example.fullstack_backend.exception.UserNotFoundException;
import com.example.fullstack_backend.factory.UserFactory;
import com.example.fullstack_backend.model.User;
import com.example.fullstack_backend.model.UserDTO;
import com.example.fullstack_backend.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by User: Vu
 * Date: 19.12.2023
 * Time: 18:55
 */
@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {


    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/user")
    User newUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @GetMapping("/users")
    List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOS = users.stream().map(UserFactory::fromEntity).collect(Collectors.toList());

        return userDTOS;
    }

    @GetMapping("/user/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return UserFactory.fromEntity(user);
    }

    @PutMapping("/user/{id}")
    public UserDTO updateUser(@RequestBody UserDTO newUserDTO, @PathVariable Long id) {
        User updatedUser = userRepository.findById(id)
                .map(user -> {
                    // Use UserFactory to fill in the entity with DTO data
                    UserFactory.fillEntity(user, newUserDTO);
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new UserNotFoundException(id));
        // Return a DTO representation of the updated user
        return UserFactory.fromEntity(updatedUser);
    }


    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "user with id: " + id + " has been deleted";
    }

}
