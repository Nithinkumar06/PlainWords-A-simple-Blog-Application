package com.st.blog.controller;


import com.st.blog.beans.User;
import com.st.blog.exceptions.UserNotFound;
import com.st.blog.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog/api/users")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registerAsNewUser")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<User> getUserProfile(@PathVariable Long id) throws UserNotFound {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<User> updateUserProfile(@PathVariable Long id, @RequestBody User user) throws UserNotFound{
        return ResponseEntity.ok(userService.updateUser(id, user));
    }
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<User> deleteUserProfile(@PathVariable Long id) throws UserNotFound {
        return ResponseEntity.ok(userService.deleteUserById(id));
    }
//    @GetMapping("/{id}/posts")
//    public ResponseEntity<List<?>> getUserPosts(@PathVariable Long id) {
//        return ResponseEntity.ok(userService.getUserPosts(id));
//    }
}