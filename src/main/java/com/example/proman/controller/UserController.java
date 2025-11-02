package com.example.proman.controller;

import com.example.proman.entity.UserEntity;
import com.example.proman.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserEntity createPost(@RequestBody UserEntity user) {
        System.out.println("disini");
        return userService.save(user);
    }

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userService.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        return userService.findById(id)
                .map(user -> {
            userService.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
        })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/login")
    public UserEntity loginUser(@RequestBody UserEntity user) {
        return userService.login(user);
    }

}
