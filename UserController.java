package com.niit.springBootUserAuthentication.controller;


import com.niit.springBootUserAuthentication.model.User;
import com.niit.springBootUserAuthentication.service.UserSecurityTokenGenerator;
import com.niit.springBootUserAuthentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")//Handling CORS exception
@RestController
@RequestMapping("/niit/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSecurityTokenGenerator userSecurityTokenGenerator;

    @GetMapping("/get")
    public ResponseEntity<?>getAllUser(){
        return new ResponseEntity<>(userService.getUser(), HttpStatus.FOUND);
    }

    @PostMapping("/register")
    public ResponseEntity<?>insertUser(@RequestBody User user){
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?>userAuth(@RequestBody User user){
        User userObj = userService.authCheck(user.getEmail(),user.getPassword());
        //userObj will fetch the object of user if id and password is correct
        if(userObj!=null){
            Map<String,String> key = userSecurityTokenGenerator.tokenGenerator(user);
            return new ResponseEntity<>(key,HttpStatus.OK);
        }else{
            //if userObj is null, user is unauthorized
            return new ResponseEntity<>("User not authorized",HttpStatus.NOT_FOUND);
        }
    }
}
