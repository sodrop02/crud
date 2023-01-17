package com.sodrop.fullstackbackend.controller;

import com.sodrop.fullstackbackend.model.User;
import com.sodrop.fullstackbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.sodrop.fullstackbackend.exception.UserNotFoundException;

@RestController
@CrossOrigin("http://localhost:3000")
public interface UserController {

    @PostMapping("/user")
    @Transactional
    User newUser(@RequestBody User newUser);
    @GetMapping("/users")
    List<User> getAllUsers();
    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id);
    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id);
    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id);
}