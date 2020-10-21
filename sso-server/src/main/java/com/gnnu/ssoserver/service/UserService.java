package com.gnnu.ssoserver.service;

import com.gnnu.ssoserver.entity.Manager;
import com.gnnu.ssoserver.entity.User;

public interface UserService {
    User checkUserAndPassword(String username, String password);//检查普通用户登录密码

    void registerUser(User user);


}
