package com.niit.springBootUserAuthentication.service;

import com.niit.springBootUserAuthentication.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserSecurityTokenGeneratorImpl implements UserSecurityTokenGenerator {


    //generating token using map collection,return key-value pair
    @Override
    public Map<String, String> tokenGenerator(User user) {

        Map<String,String> map = new HashMap<>();
        String jwtToken = Jwts.builder().setIssuer("myApp") //identifies subject of JWT
//                .setSubject(user.getUserId())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512,"mysecret")
                .compact();

        //put will display content on screen
        map.put("token",jwtToken);  //put method will take the key value pair
        map.put("userEmail",user.getEmail());
        map.put("Message","LoggedIn successfully");
        return map;
    }

//
//    @Override
//    public Map<String, String> tokenGeneration(User user) {
//        Map<String,String> result=new HashMap<String, String>();
//
//        Map<String,Object> data = new HashMap<>();
//        data.put("userObject",user);
//
//        String jwtToken = Jwts.builder().setClaims(data).
//                setIssuedAt(new Date()).
//                signWith(SignatureAlgorithm.HS512,"mysecuritykey").
//                compact();
//
//        result.put("token",jwtToken);
//
//        return result;
//    }
}
