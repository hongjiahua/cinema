package com.gnnu.ssoserver.controller;

import com.gnnu.ssoserver.entity.Man;
import com.gnnu.ssoserver.service.ManagerService;
import com.gnnu.ssoserver.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/sso")
@CrossOrigin("*")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private ManagerService managerService;
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletResponse response, Map<String, Object> map, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam(value = "url", required = false) String url) throws IOException {
        System.out.println(managerService.checkManagerAndPassword(username, password));
        String token = "";
        String datamap = "";
        long maxeExistTime = 3 * 60;//cookie和redis最大存在时间
        Man user = null;
        if (null != (user = checkUserAndPassword(username, password))) {
            token = username + System.currentTimeMillis();
            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            cookie.setMaxAge((int) maxeExistTime);
            map.put("user", user);
            map.put("identity", user.identity());
            map.put("token", token);
            map.put("url", url);
            Gson gson = new Gson();
            datamap = gson.toJson(map);
            redisTemplate.opsForValue().set(token, map, maxeExistTime, TimeUnit.SECONDS);
            /*response.addCookie(cookie);
            response.sendRedirect(url);*/


        }
        return datamap;
    }

    private Man checkUserAndPassword(String username, String password) {
        Man user = null;
        System.out.println(username);
        System.out.println(password);
        try {

            if (null != (managerService.checkManagerAndPassword(username, password))) {
                user = managerService.checkManagerAndPassword(username, password);
            } else if (null != (userService.checkUserAndPassword(username, password))) {
                user = userService.checkUserAndPassword(username, password);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }

    @RequestMapping("/hasKey")
    @ResponseBody
    public Boolean hasKey(@RequestParam("token") String token) {
        try {
            return redisTemplate.hasKey(token);

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;

        }

    }
}