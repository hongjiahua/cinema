package com.gnnu.ssoserver.service.impl;

import com.gnnu.ssoserver.dao.ManagerDao;
import com.gnnu.ssoserver.entity.Manager;
import com.gnnu.ssoserver.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerDao managerDao;

    @Override
    public Manager checkManagerAndPassword(String managerName, String managerPassword) {
        return managerDao.checkManagerAndPassword(managerName, managerPassword);
    }
}
