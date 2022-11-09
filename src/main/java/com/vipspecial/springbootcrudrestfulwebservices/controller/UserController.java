package com.vipspecial.springbootcrudrestfulwebservices.controller;

import com.vipspecial.springbootcrudrestfulwebservices.entity.User;
import com.vipspecial.springbootcrudrestfulwebservices.repository.UserRepository;
import com.vipspecial.springbootcrudrestfulwebservices.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    // get all users
    @GetMapping
    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    // get user by id
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable (value = "id") long userId) {
        return ResponseEntity.ok().body(userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id : ")));
    }

    // create user
    @PostMapping
    public User createUser(@RequestBody User user){
        return this.userRepository.save(user);
    }
    // update user
    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user,@PathVariable("id") long userId) throws ResourceNotFoundException {

        User existingUser = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + userId));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        return this.userRepository.save(existingUser);

    }
    // delete user by id

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable ("id") long userId) throws ResourceNotFoundException {
        User existingUser = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + userId));
         this.userRepository.delete(existingUser);
         return ResponseEntity.ok().build();
    }
}
