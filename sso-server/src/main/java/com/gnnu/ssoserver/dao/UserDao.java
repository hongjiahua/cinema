package com.gnnu.ssoserver.dao;

import com.gnnu.ssoserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface UserDao extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User>, Serializable {
    @Query("SELECT t from User t where t.username=?1 and t.password=?2")
    User checkUserAndPassword(String username, String password);
}
