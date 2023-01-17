package com.sodrop.fullstackbackend.controller;

import com.sodrop.fullstackbackend.exception.UserNotFoundException;
import com.sodrop.fullstackbackend.model.User;
import com.sodrop.fullstackbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@CrossOrigin("http://localhost:3000")
public class UserServiceImpl implements UserController{
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    @Transactional
    public User newUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }
    @GetMapping("/users")
    @Transactional(readOnly = true)
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    @Transactional(readOnly = true)
    public User getUserById(@PathVariable Long id){
        return userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    @Transactional
    public User updateUser(@RequestBody User newUser, @PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUser(newUser.getUser());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }
    @DeleteMapping("/user/{id}")
    @Transactional
    public String deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "User "+id+" has been deleted.";
    }
}
