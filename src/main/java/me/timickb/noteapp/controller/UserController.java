package me.timickb.noteapp.controller;

import me.timickb.noteapp.exception.LoginException;
import me.timickb.noteapp.exception.EntityNotFoundException;
import me.timickb.noteapp.model.User;
import me.timickb.noteapp.model.request.LoginRequest;
import me.timickb.noteapp.model.request.RegisterRequest;
import me.timickb.noteapp.model.response.UserResponse;
import me.timickb.noteapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Iterable<UserResponse> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId) throws EntityNotFoundException {
        return userService.getOne(userId);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest request) {
        try {
            return ResponseEntity.ok(userService.login(request));
        } catch(LoginException e) {
            return ResponseEntity.badRequest().body("Wrong credentials");
        }
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }
}
