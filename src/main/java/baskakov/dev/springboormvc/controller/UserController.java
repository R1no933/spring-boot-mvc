package baskakov.dev.springboormvc.controller;

import baskakov.dev.springboormvc.model.UserDTO;
import baskakov.dev.springboormvc.service.UserService;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<UserDTO> createUser(
            @RequestBody @Valid UserDTO user
    ) {
        UserDTO createdUser = userService.createUser(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdUser);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUserById(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getUserById(id));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUserById(
            @PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return ResponseEntity
                .noContent()
                .build();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDTO> updateUserById(
            @PathVariable("id") Long id,
            @RequestBody @Valid UserDTO user) {
        userService.updateUserById(id, user);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
