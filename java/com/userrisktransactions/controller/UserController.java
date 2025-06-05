package com.userrisktransactions.controller;


import com.userrisktransactions.dto.UserDto;
import com.userrisktransactions.model.User;
import com.userrisktransactions.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/")
public class UserController {

    @Autowired
    public UserService userService;


    @PostMapping("createUser")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDto userDto) {
        System.out.print("createUser");
        User createdUser = userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);

    }
    @GetMapping("getUsers")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<User>> getUsers() {
        System.out.print("createUser");
        List<User> getUsers = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(getUsers);

    }
}
