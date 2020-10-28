package com.gnnu.ssoserver.controller;

import com.gnnu.ssoserver.entity.User;
import com.gnnu.ssoserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    @ResponseBody
    public String register(User user) {
        try {
            userService.registerUser(user);
            return "success";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "failed";

        }


    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public boolean updateUser(User user) {
        try {
            userService.updateUser(user);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;

        }

    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public boolean deleteUserByName(@RequestParam("username") String username) {
        try {
            User user = userService.queryUserByName(username);
            userService.deleteUser(user);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;

        }

    }

    @RequestMapping("/queryUser")
    @ResponseBody
    public void queryUser(@RequestParam("username") String username) {
        try {
            userService.queryUserByName(username);

        } catch (Exception ex) {
            ex.printStackTrace();

        }

    }

    @RequestMapping("/queryUserById")
    @ResponseBody
    public void queryUserById(@RequestParam("userId") Integer userId) {
        try {
            userService.queryUserById(userId);

        } catch (Exception ex) {
            ex.printStackTrace();

        }

    }

    @RequestMapping("/listAllUser")
    @ResponseBody
    public List<User> listAllUser() {
        try {
            return userService.listAllUser();

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }
}
