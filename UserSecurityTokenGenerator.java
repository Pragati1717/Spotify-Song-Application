package com.niit.springBootUserAuthentication.service;

import com.niit.springBootUserAuthentication.model.User;

import java.util.Map;

public interface UserSecurityTokenGenerator {

    Map<String,String> tokenGenerator(User user);
}
