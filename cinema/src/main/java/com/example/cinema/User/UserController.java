package com.example.cinema.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {this.userService = userService;}

    @GetMapping
    public List<User> getUser(){return userService.getUsers();}

    @GetMapping("/{id}")
    public User getUser(@PathVariable String id){return userService.getUser(id);}

    @PostMapping
    public User createUser(@RequestBody User user){return userService.createUser(user);}

    @DeleteMapping
    public void deleteUser (@PathVariable String id){userService.deleteUser(id);}

    @PutMapping("/{id}")
    public void updateUser (@PathVariable String id, @RequestBody User data){userService.updateUser(id, data);}
}