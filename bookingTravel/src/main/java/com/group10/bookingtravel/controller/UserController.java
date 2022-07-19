package com.group10.bookingtravel.controller;

import antlr.collections.impl.LList;
import com.group10.bookingtravel.entity.User;
import com.group10.bookingtravel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.group10.bookingtravel.dto.UserSearchDTO;
import com.group10.bookingtravel.dto.UserStatusDTO;
import com.group10.bookingtravel.entity.User;
import com.group10.bookingtravel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getListUserStatus(@RequestParam(name = "id" , required = false) String id,
                                        @RequestParam(name = "username", required = false) String username,
                                        @RequestParam(name = "rank" , required = false) String rank,
                                        @RequestParam(name = "status", required = false) String status){
        return userService.getListUserStatus(id,username,rank,status);
    }

    @PostMapping("/user/status")
    public void updateStatusUser(@RequestBody UserStatusDTO userStatusDTO){
      userService.updateStatusUser(userStatusDTO);
    }

    @GetMapping("/user/{userName}")
    public User getUserByUserName(@PathVariable String userName){
        return userService.getUserByUserName(userName);
    }
}
