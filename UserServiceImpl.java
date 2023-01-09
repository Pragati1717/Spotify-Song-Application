package com.niit.springBootUserAuthentication.service;

import com.niit.springBootUserAuthentication.model.User;
import com.niit.springBootUserAuthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    //get
    @Override
    public List<User> getUser(){
        return userRepository.findAll();
    }
    //post
    @Override
    public User addUser(User user) {
        user.setRole("USER_ROLE");
        return userRepository.save(user);
    }

    @Override
    public User authCheck(String email, String pass) {

        //checking user in database..if user is present or not..
        if(userRepository.findById(email).isPresent()){

            //checking password
            User user = userRepository.findById(email).get();
            if(user.getPassword().equals(pass)){
                return user;
            }else{
                //user was in database but wrong password
                return null;
            }
        }else{
            //user is not is database
            return null;
        }
    }
}
