package com.gnnu.ssoserver.service;

import com.gnnu.ssoserver.entity.Manager;

public interface ManagerService {
    Manager checkManagerAndPassword(String managerName, String managerPassword);//检查管理员登录密码
}
