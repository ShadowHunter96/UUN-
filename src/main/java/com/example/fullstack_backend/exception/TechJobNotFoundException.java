package com.example.fullstack_backend.exception;

/**
 * Created by User: Vu
 * Date: 22.02.2024
 * Time: 20:14
 */
public class TechJobNotFoundException extends RuntimeException{
    public TechJobNotFoundException(Long id){
        super("Could not find the tech with id " + id);
    }
}
