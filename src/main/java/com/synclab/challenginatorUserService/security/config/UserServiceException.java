package com.synclab.challenginatorUserService.security.config;



public class UserServiceException extends RuntimeException{

    public UserServiceException(String message)
    {
        super(message);
    }
}