package com.example.fullstack_backend.exception;

/**
 * Created by User: Vu
 * Date: 20.12.2023
 * Time: 15:18
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id){
        super("Could not find the user with id " + id);
    }
}
