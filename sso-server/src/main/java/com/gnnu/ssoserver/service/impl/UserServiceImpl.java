package com.gnnu.ssoserver.service.impl;

import com.gnnu.ssoserver.dao.UserDao;
import com.gnnu.ssoserver.entity.User;
import com.gnnu.ssoserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User checkUserAndPassword(String username, String password) {
        return userDao.checkUserAndPassword(username, password);
    }
}
