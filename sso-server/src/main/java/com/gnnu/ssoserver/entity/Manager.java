package com.gnnu.ssoserver.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "manager")
@Data
public class Manager implements Man, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Manager_ID")
    private Integer managerId;
    @Column(name = "Manager_Name")
    private String managerName;
    @Column(name = "Manager_Password")
    private String managerPassword;

    @Override
    public String identity() {
        return "管理员";
    }
}
