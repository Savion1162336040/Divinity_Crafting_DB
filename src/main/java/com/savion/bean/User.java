package com.savion.bean;

import org.nutz.dao.entity.annotation.*;

/**
 * Created by savion on 2018/2/9.
 */
@Table(value = "user")
public class User {
    @Id
    @Column(value = "id")
    private int id;
    @Name
    @Column(value = "name")
    private String name;
    @Column(value = "password")
    private String password;
    @Column(value = "create_time")
    private String createTime;
    @Column(value = "update_time")
    private String updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
