package com.gnnu.ssoserver.service.impl;

import com.gnnu.ssoserver.dao.UserDao;
import com.gnnu.ssoserver.entity.User;
import com.gnnu.ssoserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Override
    public void updateUser(User user) {
        userDao.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userDao.delete(user);
    }

    @Override
    public Page<User> listAllUser(int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);
        return userDao.findAll(pageable);
    }

    @Override
    public User queryUserByName(String username) {
        return userDao.queryUserByName(username);
    }

    @Override
    public User queryUserById(Integer uid) {
        return userDao.queryUserById(uid);
    }

}
