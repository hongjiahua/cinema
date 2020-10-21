package com.gnnu.ssoserver.dao;

import com.gnnu.ssoserver.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerDao extends JpaSpecificationExecutor<Integer>, JpaRepository<Manager, Integer> {
    @Query("SELECT t from Manager t where t.managerName=?1 and t.managerPassword=?2")
    Manager checkManagerAndPassword(String managerName, String managerPassword);//检查管理员登录密码
}
