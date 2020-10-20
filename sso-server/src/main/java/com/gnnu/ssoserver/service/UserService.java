package com.gnnu.ssoserver.service;

import com.gnnu.ssoserver.entity.User;

public interface UserService {
    User checkUserAndPassword(String username, String password);
}
