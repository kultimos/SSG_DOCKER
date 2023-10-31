package com.kul.controller;

import com.kul.pojo.User;
import com.kul.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
public class UserController {
    @Resource
    private IUserService userService;

    @GetMapping("/user/add")
    public void addUser() {
        for(int i=1; i<=3; i++) {
            User user = new User();
            user.setUsername("test" + i);
            user.setPassword("pass");
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            userService.insert(user);
        }
    }

    @GetMapping("/user/find/{id}")
    public User findUserById(@PathVariable Long id) {
        return userService.selectOne(id);
    }
}
