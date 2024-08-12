package erenculhaci.tunebox.controller;

import erenculhaci.tunebox.dto.UserDTO;
import erenculhaci.tunebox.dto.UserResponseDTO;
import erenculhaci.tunebox.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/createUser")
    public ResponseEntity<Boolean> createUser(@RequestBody UserDTO userDTO) {
        Boolean created = userService.createUser(userDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/getUserById")
    public ResponseEntity<UserResponseDTO> getUserById(@RequestParam Long id) {
        UserResponseDTO userById = userService.getUserById(id);
        return new ResponseEntity<>(userById, HttpStatus.OK);
    }

    @GetMapping("/getUserByUsername")
    public ResponseEntity<UserResponseDTO> getUserByUsername(@RequestParam String username) {
        UserResponseDTO userByUsername = userService.getUserByUsername(username);
        return new ResponseEntity<>(userByUsername, HttpStatus.OK);
    }

    @GetMapping("/getAllUsersBySubscription")
    public ResponseEntity<List<UserResponseDTO>> getAllUsersBySubscription(@RequestParam String subscriptionType) {
        List<UserResponseDTO> allUsersBySubscription = userService.getAllUsersBySubscription(subscriptionType);
        return new ResponseEntity<>(allUsersBySubscription, HttpStatus.OK);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<Boolean> updateUser(@RequestParam String username, @RequestParam String currentPassword, @RequestBody UserDTO userDTO) {
        Boolean updated = userService.updateUser(username, currentPassword, userDTO);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser")
    public ResponseEntity<Void> deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
