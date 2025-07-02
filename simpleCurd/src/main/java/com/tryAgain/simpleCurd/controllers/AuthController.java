package com.tryAgain.simpleCurd.controllers;

import com.tryAgain.simpleCurd.entity.UserEntity;
import com.tryAgain.simpleCurd.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public  ResponseEntity<Map<String,String>>  login(@RequestBody UserEntity user){
        //Authenciation the user

        try {

            Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            UserDetails userDetails= (UserDetails)authentication.getPrincipal();

            String token=  jwtUtil.generateJwtToken(userDetails);

            return ResponseEntity.ok(Map.of("token", token));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials Username and Password"));
        }





    }


}
