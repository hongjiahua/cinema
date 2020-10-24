package com.gnnu.ssoserver.dao;

import com.gnnu.ssoserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface UserDao extends CrudRepository<User, Integer>, JpaRepository<User, Integer>, JpaSpecificationExecutor<User>, Serializable {
    @Query("SELECT t from User t where t.username=?1 and t.password=?2")
    User checkUserAndPassword(String username, String password);//检查普通用户登录密码

    @Query("SELECT t from User t where t.username=?1")
    User queryUserByName(String username);

    @Query("SELECT t from User t where t.uid=?1")
    User queryUserById(Integer uid);
}
