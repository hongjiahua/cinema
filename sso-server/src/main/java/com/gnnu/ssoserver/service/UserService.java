package com.gnnu.ssoserver.service;

import com.gnnu.ssoserver.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface UserService {
    User checkUserAndPassword(String username, String password);//检查普通用户登录密码

    void registerUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

    Page<User> listAllUser(int page, int size, Sort sort);

    User queryUserByName(String username);

    User queryUserById(Integer uid);
}
