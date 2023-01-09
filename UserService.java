package com.niit.springBootUserAuthentication.service;

import com.niit.springBootUserAuthentication.model.User;

import java.util.List;

public interface UserService {

    //get
    public List<User> getUser();

    //post
    public User addUser(User user);

    //post
    public User authCheck(String email, String pass);
}
