package com.example.module;

public class UserNotFoundException extends RuntimeException{

   public UserNotFoundException(String message){
        super(message);
   }
}
