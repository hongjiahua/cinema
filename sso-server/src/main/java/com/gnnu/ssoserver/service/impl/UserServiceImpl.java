package com.gnnu.ssoserver.service.impl;

import com.gnnu.ssoserver.dao.UserDao;
import com.gnnu.ssoserver.entity.User;
import com.gnnu.ssoserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User checkUserAndPassword(String username, String password) {
        return userDao.checkUserAndPassword(username, password);
    }

    @Modifying
    @Transactional
    @Override
    public void registerUser(User user) {
        userDao.save(user);
    }

}
