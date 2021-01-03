package com.example.demo;

import com.example.module.User;
import com.example.module.UserErrorResponse;
import com.example.module.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
private List<User> usersList;

// load date
    @PostConstruct
    public void loadData(){
        usersList=new ArrayList<>();
        usersList.add(new User(1,"zaki",16));
        usersList.add(new User(2,"karim",23));
        usersList.add(new User(3,"amira",22));
        usersList.add(new User(4,"john",26));
    }
    @RequestMapping("/users")
    public List<User> getUsers(){
        return usersList;
    }

    @RequestMapping("/user/{id}")
    public User getUser(@PathVariable int id){
        if(id>= usersList.size() || id<0){
            throw new UserNotFoundException("User Not Found "+id);
        }
        return usersList.get(id);
    }
    // add exception handler using @ExceptionHandler
    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleException(UserNotFoundException userNotFoundException){
        // creation d'une response
        UserErrorResponse error = new UserErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(userNotFoundException.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        // return ResponseEntity
        // jackson convert error to JSON automaticly (error <-> response body)
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}
