package com.tryAgain.simpleCurd.controllers;

import com.tryAgain.simpleCurd.entity.UserEntity;
import com.tryAgain.simpleCurd.exceptions.ResourceNotFoundException;
import com.tryAgain.simpleCurd.model.User;
import com.tryAgain.simpleCurd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


//    @GetMapping
//    public  String getUsers()
//    {
//        return "Hello World";
//    }

    @GetMapping
    public List<UserEntity>getUsers()
    {

        return  userRepository.findAll();

    }

    //        return Arrays.asList(new User(1L, "firstName", "email"),new User(2L, "abc", "abc@gmail.com"));

    @PostMapping
    public UserEntity createUser(@RequestBody UserEntity userEntity) {

        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

       return userRepository.save(userEntity);
    }

    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found"+id));

    }
    @PutMapping("/{id}")
    public  UserEntity updateUser(@PathVariable Long id, @RequestBody UserEntity userEntity)
    {
     UserEntity  userDate= userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found"+id));
     userDate.setFirstName(userEntity.getFirstName());
     userDate.setEmail(userEntity.getEmail());
        return userRepository.save(userDate);

    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<?> deleteUserById(@PathVariable Long id) {
      UserEntity userData = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not found"+id));
      userRepository.delete(userData);
      return ResponseEntity.ok().build();
    }

}
