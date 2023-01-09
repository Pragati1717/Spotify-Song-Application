package com.niit.springBootUserAuthentication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data //for getter-setter-toString

public class User {

    @Id
    private String email;
    private String password;
    private String role;
}
