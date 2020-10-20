package com.gnnu.ssoserver.controller;

import com.gnnu.ssoserver.entity.User;
import com.gnnu.ssoserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/sso")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletResponse response, Map<String, Object> map, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("url") String url) throws IOException {
        String token = "";
        if (null != checkUserAndPassword(username, password)) {
            token = username + System.currentTimeMillis();
            User user = userService.checkUserAndPassword(username, password);
            Cookie cookie = new Cookie("token", token);
            map.put(token, user);
            redisTemplate.opsForValue().set(token, map, (long) 5 * 60, TimeUnit.SECONDS);
            System.out.println(token);
            /*response.addCookie(cookie);
            response.sendRedirect(url);*/


        }
        return token;
    }

    private User checkUserAndPassword(String username, String password) {
        User user = userService.checkUserAndPassword(username, password);
        return user;

    }

    @RequestMapping("/hasKey/{key}")
    @ResponseBody
    public Boolean hasKey(@PathVariable("key") String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;

        }

    }
}